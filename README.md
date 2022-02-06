## Camunda samples
### Includes
- Camunda-engine: Springboot microservice with embedded Camunda Engine (uses in-memory H2 database)
- ExternalTaskClient: Springboot microservice with provides implementation for an external task service is Camunda
- models: sample BMPM and DMN models. The BPMN model includes a Business Rule Task(DMN)
- Camunda.postman_collection.json: Postman collection to test the sample
### Camunda Console
- url: http://localhost:8080/camunda/app/welcome/default/#!/login
- user/password: demo/demo
### Camunda H2 database
- url: http://localhost:8080/h2-console
- username/password: blank
- A few tables:
  - ACT_GE_BYTEARRAY: Stores the bytearray of model file
  - ACT_RE_DEPLOYMENT
  - ACT_RE_PROCDEF: Stores BPMN process details like NAME, KEY, DEPLOYMENT _ID, RESOURCE_NAME
  - ACT_RE_DECISION_DEF: Stores DMN process details
