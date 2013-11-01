package com.patrimony.utils;

import java.io.File;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class Xlsx {
	private String[] sharedStrings;
	private Map<String, Object> sheets = new LinkedHashMap<String, Object>();
	private static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	private String[] styles;
	private Map<String, String> numFmts;
	private XMLInputFactory factory;

	public static File createTempFile (InputStream uploadedInputStream) {
        File tempFile;
        try {
            tempFile = File.createTempFile((new Date()).toString(), ".tmp");
            tempFile.deleteOnExit();

			OutputStream out = new FileOutputStream(tempFile);
			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = uploadedInputStream.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			out.flush();
			out.close();
		} catch (Exception e) {
		    tempFile = null;
		}

		return tempFile;
    }

	public void parse(File f) throws Exception {
		ZipFile z = new ZipFile(f);
		factory = XMLInputFactory.newInstance();
		parseWorkbook(z.getInputStream(z.getEntry("xl/workbook.xml")));
		ZipEntry ss = z.getEntry("xl/sharedStrings.xml");
		if (ss != null)
			parseSharedStrings(z.getInputStream(ss));
		parseStyles(z.getInputStream(z.getEntry("xl/styles.xml")));
		for (Map.Entry e : this.sheets.entrySet()) {
			e.setValue(parseSheet(z.getInputStream(z.getEntry("xl/worksheets/sheet1.xml"))));
		}
		z.close();
	}

	public Map<String, String[][]> sheets() {
		return (Map<String, String[][]>) (Map) this.sheets;
	}

	private int[][] range2num(String r) {
		String[] rs = r.split("[:]");
		int[][] ret = new int[2][];
		ret[0] = ref2num(rs[0]);
		if (rs.length > 1) {
			ret[1] = ref2num(rs[1]);
		} else
			ret[1] = ret[0];
		return ret;
	}

	private int[] ref2num(String r) {
		char c0 = r.charAt(0);
		char c1 = r.charAt(1);
		if (Character.isDigit(c1))
			return new int[] { c0 - 'A', Integer.parseInt(r.substring(1)) - 1 };
		else
			return new int[] { (c0 - 'A' + 1) * 26 + (c1 - 'A'), Integer.parseInt(r.substring(2)) - 1 };
	}

	private void parseWorkbook(InputStream inputStream) throws Exception {
		XMLStreamReader r = factory.createXMLStreamReader(inputStream);
		while (r.hasNext()) {
			r.next();
			if (r.getEventType() == XMLStreamConstants.START_ELEMENT) {
				if (r.getLocalName().equals("sheet")) {
					sheets.put(
							r.getAttributeValue(null, "name"),
							r.getAttributeValue("http://schemas.openxmlformats.org/officeDocument/2006/relationships",
									"id").substring(3));
				}
			}
		}
		r.close();
	}

	private void parseStyles(InputStream inputStream) throws Exception {
		XMLStreamReader r = factory.createXMLStreamReader(inputStream);
		while (r.hasNext()) {
			r.next();
			if (r.getEventType() == XMLStreamConstants.START_ELEMENT) {
				if (r.getLocalName().equals("numFmts")) {
					numFmts = new HashMap<String, String>();
					while (r.hasNext()) {
						r.next();
						if (r.getEventType() == XMLStreamConstants.START_ELEMENT && r.getLocalName().equals("numFmt"))
							numFmts.put(r.getAttributeValue(null, "numFmtId"), r.getAttributeValue(null, "formatCode"));
						else if (r.getEventType() == XMLStreamConstants.END_ELEMENT
								&& r.getLocalName().equals("numFmts")) {
							break;
						}
					}
				} else if (r.getLocalName().equals("cellXfs")) {
					styles = new String[Integer.parseInt(r.getAttributeValue(null, "count"))];
					int i = 0;
					while (r.hasNext()) {
						r.next();
						if (r.getEventType() == XMLStreamConstants.START_ELEMENT && r.getLocalName().equals("xf"))
							styles[i++] = r.getAttributeValue(null, "numFmtId");
						else if (r.getEventType() == XMLStreamConstants.END_ELEMENT
								&& r.getLocalName().equals("cellXfs")) {
							break;
						}
					}
				}
			}
		}
		r.close();
	}

	private boolean isDateStyle(String style) {
		String s = styles[Integer.valueOf(style)];
		if (s.equals("14"))
			return true;
		String numFmt = this.numFmts.get(s);
		if (numFmt != null) {
			return numFmt.contains("d") && numFmt.contains("m") && numFmt.contains("y");
		}
		return false;
	}

	private String[][] parseSheet(InputStream is) throws Exception {
		XMLStreamReader r = factory.createXMLStreamReader(is);
		String[][] data = null;
		while (r.hasNext()) {
			r.nextTag();
			if (r.getLocalName().equals("dimension")) {
				int[][] range = range2num(r.getAttributeValue(null, "ref"));
				data = new String[range[1][1] + 1][range[1][0] + 1];
				break;
			}
		}
		Calendar c = Calendar.getInstance();
		while (r.hasNext()) {
			r.next();
			if (r.getEventType() == XMLStreamConstants.START_ELEMENT) {
				if (r.getLocalName().equals("c")) {
					int[] ref = ref2num(r.getAttributeValue(null, "r"));
					String type = r.getAttributeValue(null, "t");
					String style = r.getAttributeValue(null, "s");
					while (true) {
						r.next();
						if (r.getEventType() == XMLStreamConstants.END_ELEMENT && r.getLocalName().equals("c")) {
							break;
						} else if (r.getEventType() == XMLStreamConstants.START_ELEMENT) {
							if (r.getLocalName().equals("v")) {
								String v = r.getElementText();
								if (type != null) {
									if (type.equals("s")) {
										data[ref[1]][ref[0]] = this.sharedStrings[Integer.parseInt(v)];
									} else {
										data[ref[1]][ref[0]] = v;
									}
								} else if (style != null) {
									if (isDateStyle(style)) {
										// TODO 1900-02-29
										c.set(1900, 0, Integer.parseInt(v) - 1, 0, 0, 0);
										data[ref[1]][ref[0]] = dateFormat.format(c.getTime());
									} else {
										data[ref[1]][ref[0]] = v;
									}
								} else
									data[ref[1]][ref[0]] = v;
							}
						}
					}
				}
			}
		}
		r.close();
		return data;
	}

	private void parseSharedStrings(InputStream inputStream) throws Exception {
		XMLStreamReader r = factory.createXMLStreamReader(inputStream);
		int index = 0;
		r.nextTag();
		if (!r.getLocalName().equals("sst"))
			throw new AssertionError();
		sharedStrings = new String[Integer.parseInt(r.getAttributeValue(null, "count"))];
		while (r.hasNext()) {
			r.next();
			if (r.getEventType() == XMLStreamConstants.START_ELEMENT) {
				if (r.getLocalName().equals("si")) {
					StringBuilder sb = new StringBuilder();
					while (true) {
						r.next();
						if (r.getEventType() == XMLStreamConstants.END_ELEMENT && r.getLocalName().equals("si"))
							break;
						else if (r.getEventType() == XMLStreamConstants.CHARACTERS) {
							sb.append(r.getText());
						}
					}
					sharedStrings[index++] = sb.toString();
				}
			}
		}
		r.close();
	}

}
