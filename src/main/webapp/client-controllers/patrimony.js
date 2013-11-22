/*global angular:false, FileReader:false, document:false*/
angular.module('app.patrimony', []).config(function ($routeProvider) {
    'use strict';

    $routeProvider.when('/enviar-arquivo', {
        templateUrl : '/client-views/send-file.tpl.html',
        controller : function ($rootScope, $scope, $http, $location) {
            if (!$rootScope.user) {
                return $location.path('/entrar');
            }

            $scope.save = function () {
                var reader = new FileReader();
                $rootScope.loading = true;

                reader.onload = function(file) {
                    $http.get('api/patrimonies').success(function (data) {
                        var i;
                        var errors = [];
                        var oldPatrimonies = {};
                        var patrimonies = [];
                        var records = file.target.result.split('\n');

                        for (i = 0; i < data.length; i+= 1) {
                            oldPatrimonies[data[i].id] = data[i];
                        }
                        for (i = 1; i < records.length; i+=1) {
                            var record = records[i].split(/,(?=(?:[^\"]*\"[^\"]*\")*(?![^\"]*\"))/);
                            var patrimony = {
                                'id' : record[1],
                                'description' : record[2],
                                'department' : record[0],
                                'mark' : record[3],
                                'model' : record[4],
                                'serialNumber' : record[5],
                                'acquisitionDate' : record[6],
                                'closingDate' : record[7],
                                'value' : record[8],
                                'process' : record[9],
                                'document' : record[10],
                                'building' : record[11],
                                'floor' : record[12],
                                'complement' : record[13],
                                'situation' : record[14]
                            };
                            var oldData = oldPatrimonies[record[1]];
                            if (oldData) {
                                if (oldData.description !== patrimony.description) errors.push('A descrição do item ' + patrimony.id + ' esta conflitante com os dados do banco');
                                if (oldData.department !== patrimony.department) errors.push('O departamento do item ' + patrimony.id + ' esta conflitante com os dados do banco');
                                if (oldData.mark !== patrimony.mark) errors.push('A marca do item ' + patrimony.id + ' esta conflitante com os dados do banco');
                                if (oldData.model !== patrimony.model) errors.push('O modelo do item ' + patrimony.id + ' esta conflitante com os dados do banco');
                                if (oldData.serialNumber !== patrimony.serialNumber) errors.push('O serial number do item ' + patrimony.id + ' esta conflitante com os dados do banco');
                                if (oldData.acquisitionDate !== patrimony.acquisitionDate) errors.push('A data de aquisição do item ' + patrimony.id + ' esta conflitante com os dados do banco');
                                if (oldData.closingDate !== patrimony.closingDate) errors.push('A data de fechamento do item ' + patrimony.id + ' esta conflitante com os dados do banco');
                                if (oldData.value !== patrimony.value) errors.push('O valor do item ' + patrimony.id + ' esta conflitante com os dados do banco');
                                if (oldData.process !== patrimony.process) errors.push('O processo do item ' + patrimony.id + ' esta conflitante com os dados do banco');
                                if (oldData.document !== patrimony.document) errors.push('O documento do item ' + patrimony.id + ' esta conflitante com os dados do banco');
                                if (oldData.building !== patrimony.building) errors.push('O prédio do item ' + patrimony.id + ' esta conflitante com os dados do banco');
                                if (oldData.floor !== patrimony.floor) errors.push('O andar do item ' + patrimony.id + ' esta conflitante com os dados do banco');
                                if (oldData.complement !== patrimony.complement) errors.push('O complemento do item ' + patrimony.id + ' esta conflitante com os dados do banco');
                                if (oldData.situation !== patrimony.situation) errors.push('A situação do item ' + patrimony.id + ' esta conflitante com os dados do banco');
                            } else {
                                patrimonies.push(patrimony);
                            }
                        }
                        if (errors.length > 0) {
                            $rootScope.loading = false;
                            $rootScope.alert = errors.join('. ');
                        } else {
                            $http.post('/api/patrimonies', patrimonies).success(function () {
                                $rootScope.loading = false;
                                $rootScope.message = 'planilha importada com sucesso';
                                $location.path('/consultar-patrimonio');
                            }).error(function () {
                                $rootScope.loading = false;
                                $rootScope.alert = 'ocorreu um erro no servidor';
                            });
                        }
                    }).error(function () {
                        $rootScope.loading = false;
                        $rootScope.alert = 'ocorreu um erro no servidor';
                    });
                };
                reader.readAsBinaryString(document.getElementById('file').files[0]);
            };
        }
    }).
    when('/patrimonio/:id', {
        templateUrl : '/client-views/patrimony-details.tpl.html',
        controller : function ($rootScope, $scope, $http, $location, $routeParams) {
            if (!$rootScope.user) {
                return $location.path('/entrar');
            }

            $http.get('/api/patrimonies/' + $routeParams.id).success(function (data) {
                $scope.patrimony = data;
                switch (data.building) {
                case '74' :
                    $scope.patrimony.building = 'IC1';
                    break;
                case '75' :
                    $scope.patrimony.building = 'IC2';
                    break;
                case '711' :
                    $scope.patrimony.building = 'IC3';
                    break;
                case '1366' :
                    $scope.patrimony.building = 'IC3.5';
                    break;
                case '724' :
                    $scope.patrimony.building = 'INOVA';
                    break;
                }
            }).error(function () {
                $rootScope.alert = 'ocorreu um erro no servidor';
            });
        }
    }).
    when('/consultar-patrimonio', {
        templateUrl : '/client-views/patrimony-list.tpl.html',
        controller : function ($rootScope, $scope, $http, $location) {
            var patrimonies;
            $rootScope.loading = true;

            if (!$rootScope.user) {
                return $location.path('/entrar');
            }

            $scope.sort = function (param) {
                $scope.patrimonies.sort(function (a,b) {
                    if (a[param] > b[param]) {return  1;}
                    if (a[param] < b[param]) {return -1;}
                    return 0;
                });
            };

            $scope.filter = function (form) {
                form = form || {};
                $rootScope.loading = false;
                $scope.patrimonies = patrimonies.filter(function (patrimony) {
                    return (!form.id          || (patrimony.id          && patrimony.id.toLowerCase().search(form.id.toLowerCase()) > -1)) &&
                           (!form.process     || (patrimony.process     && patrimony.process.toLowerCase().search(form.process.toLowerCase()) > -1)) &&
                           (!form.mark        || (patrimony.mark        && patrimony.mark.toLowerCase().search(form.mark.toLowerCase()) > -1)) &&
                           (!form.model       || (patrimony.model       && patrimony.model.toLowerCase().search(form.model.toLowerCase()) > -1)) &&
                           (!form.description || (patrimony.description && patrimony.description.toLowerCase().search(form.description.toLowerCase()) > -1));
                }).slice(0,50);
            };

            $http.get('api/patrimonies').success(function (data) {
                patrimonies = data;
                $scope.filter();
            }).error(function () {
                $rootScope.alert = 'ocorreu um erro no servidor';
            });
        }
    });

});
