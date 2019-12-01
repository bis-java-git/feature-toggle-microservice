<h1> Feature Toggle Service by Hemant Joshi</h1>
<b>
A very simple Feature Toggle Rest service. It can be considered as micro-service or a simple web service.
</b>
<br/>

<h2>Insert a featureToggle</h2>
curl -i -X POST -H "Content-Type: application/json" -d "{\"name\":\"hemant\", \"enabled\":\"true\"}" http://localhost:8080/featureToggles
<br/>

<h2>update a featureToggle</h2>
curl -i -X POST -H "Content-Type: application/json" -d "{\"name\":\"hemant\", \"enabled\":\"false\"}" http://localhost:8080/featureToggles
<br/>

<h2>delete a featureToggle</h2>
curl -i -X DELETE -H "Content-Type: application/json" -d "{\"name\":\"hemant\", \"enabled\":\"false\"}" http://localhost:8080/featureToggles
<br/>

<h2>>View featureToggles</h2>
curl -i -X GET -H "Content-Type: application/json" http://localhost:8080/featureToggles
<br/>

