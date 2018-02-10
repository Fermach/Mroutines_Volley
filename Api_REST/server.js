var express = require('express'),
  app = express(),
  port = process.env.PORT || 5000,
  mongoose = require('mongoose'),
  Rutina = require('./modelos/modeloRutina'), //
  Ejercicio = require('./modelos/modeloEjercicio'), //created model loading here
  bodyParser = require('body-parser');
  
// mongoose instance connection url connection
mongoose.Promise = global.Promise;
mongoose.connect('mongodb://localhost/mroutines'); 


app.use(bodyParser.urlencoded({ extended: true }));
app.use(bodyParser.json());


var routes= require('./rutas/rutas.js');
routes(app);


app.listen(port);
console.log("Server Listening on: " + port);
