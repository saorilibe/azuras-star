
"use strict";

// --------
// WARNING:
// --------

// THIS CODE IS ONLY MADE AVAILABLE FOR DEMONSTRATION PURPOSES AND IS NOT SECURE!
// DO NOT USE IN PRODUCTION!

// FOR SECURITY REASONS, USING A JAVASCRIPT WEB APP HOSTED VIA THE CORDA NODE IS
// NOT THE RECOMMENDED WAY TO INTERFACE WITH CORDA NODES! HOWEVER, FOR THIS
// PRE-ALPHA RELEASE IT'S A USEFUL WAY TO EXPERIMENT WITH THE PLATFORM AS IT ALLOWS
// YOU TO QUICKLY BUILD A UI FOR DEMONSTRATION PURPOSES.

// GOING FORWARD WE RECOMMEND IMPLEMENTING A STANDALONE WEB SERVER THAT AUTHORISES
// VIA THE NODE'S RPC INTERFACE. IN THE COMING WEEKS WE'LL WRITE A TUTORIAL ON
// HOW BEST TO DO THIS.

const app = angular.module('demoAppModule', ['ui.bootstrap']);

// Fix for unhandled rejections bug.
app.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}]);

app.controller('DemoAppController', function($http, $location, $uibModal) {
    const demoApp = this;

    // We identify the node.
    const apiBaseURL = "/api/resources/";
    let peers = [];

    $http.get(apiBaseURL + "me").then((response) => demoApp.thisNode = response.data.me);

    $http.get(apiBaseURL + "peers").then((response) => peers = response.data.peers);

    demoApp.openModal = () => {
        const modalInstance = $uibModal.open({
            templateUrl: 'demoAppModal.html',
            controller: 'ModalInstanceCtrl',
            controllerAs: 'modalInstance',
            resolve: {
                demoApp: () => demoApp,
                apiBaseURL: () => apiBaseURL,
                peers: () => peers
            }
        });

        modalInstance.result.then(() => {}, () => {});
    };

    demoApp.getIOUs = () => $http.get(apiBaseURL + "ious")
        .then((response) => demoApp.ious = Object.keys(response.data)
            .map((key) => response.data[key].state.data)
            .reverse());

    demoApp.getMyIOUs = () => $http.get(apiBaseURL + "my-ious")
        .then((response) => demoApp.myious = Object.keys(response.data)
            .map((key) => response.data[key].state.data)
            .reverse());

    demoApp.getIOUs();
    demoApp.getMyIOUs();

});

app.controller('ModalInstanceCtrl', function ($http, $location, $uibModalInstance, $uibModal, demoApp, apiBaseURL, peers) {
    const modalInstance = this;

    modalInstance.peers = peers;
    modalInstance.form = {};
    modalInstance.formError = false;

    // Validate and create IOU.
    modalInstance.create = () => {
        if (invalidFormInput()) {
            modalInstance.formError = true;
        } else {
            modalInstance.formError = false;

            $uibModalInstance.close();


            const createIOUEndpoint = `${apiBaseURL}create-iou?partyName=${modalInstance.form.counterparty}&iouName=${modalInstance.form.name}&iouAge=${modalInstance.form.age}&iouGender=${modalInstance.form.gender}&iouHeight=${modalInstance.form.height}&iouWeight=${modalInstance.form.weight}&iouBloodGroup=${modalInstance.form.bloodGroup}&iouDiagnosis=${modalInstance.form.diagnosis}&iouMedicine=${modalInstance.form.medicine}`;


            // Create PO and handle success / fail responses.
            $http.put(createIOUEndpoint).then(
                (result) => {
                    modalInstance.displayMessage(result);
                    demoApp.getIOUs();
                    demoApp.getMyIOUs();
                },
                (result) => {
                    modalInstance.displayMessage(result);
                }
            );
        }