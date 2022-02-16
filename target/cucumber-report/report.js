$(document).ready(function() {var formatter = new CucumberHTML.DOMFormatter($('.cucumber-report'));formatter.uri("file:src/test/resources/features/Payee.feature");
formatter.feature({
  "name": "Add new payee under pay bills",
  "description": "",
  "keyword": "Feature",
  "tags": [
    {
      "name": "@regression"
    }
  ]
});
formatter.scenario({
  "name": "Add a new payee",
  "description": "",
  "keyword": "Scenario",
  "tags": [
    {
      "name": "@regression"
    },
    {
      "name": "@bilal"
    }
  ]
});
formatter.before({
  "status": "passed"
});
formatter.step({
  "name": "Add New Payee tab",
  "keyword": "Given "
});
formatter.match({
  "location": "com.zerobank.stepdefnitions.PayeeStepDefs.add_new_payee_tab()"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "creates new payee using following information",
  "rows": [
    {},
    {},
    {},
    {}
  ],
  "keyword": "And "
});
formatter.match({
  "location": "com.zerobank.stepdefnitions.PayeeStepDefs.creates_new_payee_using_following_information(java.util.Map\u003cjava.lang.String, java.lang.String\u003e)"
});
formatter.result({
  "status": "passed"
});
formatter.step({
  "name": "message The new payee The Law Offices of Hyde, Price \u0026 Scharks was successfully created. should be displayed",
  "keyword": "Then "
});
formatter.match({
  "location": "com.zerobank.stepdefnitions.PayeeStepDefs.message_the_new_payee_the_law_offices_of_hyde_price_scharks_was_successfully_created_should_be_displayed(java.lang.String)"
});
formatter.result({
  "status": "passed"
});
formatter.after({
  "status": "passed"
});
});