var mongoose = require('mongoose');

var Rutina = mongoose.model('Rutinas');
var Ejercicio = mongoose.model('Ejercicios');

//funciona
exports.listarRutinas = function(req, res) {
  Rutina.find({}, function (err, datos){
    if (err) throw err;
    res.json(datos);
  });
};
//funciona
exports.listarEjercicios = function(req, res) {
  Ejercicio.find({}, function (err, datos){
    if (err) throw err;
    res.json(datos);
  });
};
//funciona
exports.crearRutina = function(req, res) {
    var rutina  = new Rutina(req.body);
      rutina.save(function(err, data) {
            if (err)
              res.send(err);
          console.log('Rutina añadida: ' + rutina);
          res.json({ message: 'Rutina creada correctamente' });
        
         
      });
};
//funciona
exports.crearEjercicio = function(req, res) {
    var ejercicio  = new Ejercicio(req.body);
      ejercicio.save(function(err, data) {
            if (err)
              res.send(err);
          console.log('Ejercicio añadido: ' + ejercicio);
          res.json({ message: 'Ejercicio creado correctamente' });          
          res.status(301).json(data);
      });
};

//funciona
exports.listarUnaRutina = function(req, res) {
    Rutina.find({nombre : req.params.nombre}, function(err, data) {
          if (err)
            res.send(err);
        res.json(data);
          });
};
//funciona
exports.listarEjerciciosPorRutina = function(req, res) {
    Ejercicio.find({rutina: req.params.rutina}, function(err, data) {
          if (err)
            res.send(err);
        res.json(data);
          });
};
//funciona
exports.borrarUnaRutina = function(req, res) {


    Rutina.remove({nombre : req.params.nombre}, function(err, data) {
              if (err)
            res.send(err);
        res.json({ message: 'Rutina borrada correctamente' });
		 console.log (  'Rutina eliminada' ) ;
          });
};
//funciona
exports.borrarUnEjercicio = function(req, res) {


    Ejercicio.remove({id : req.params.id}, function(err, data) {
              if (err)
            res.send(err);
        res.json({ message: 'Ejercicio borrado correctamente' });
		console.log ( 'Ejercicio eliminado' ) ;
          });
};
//funciona
exports.borrarEjerciciosPorRutina = function(req, res) {


    Ejercicio.remove({rutina : req.params.rutina}, function(err, data) {
              if (err)
            res.send(err);
        res.json({ message: 'Ejercicio/s borrado/s correctamente' });
		console.log ( 'Ejercicio/s eliminado/s' ) ;
          });
};
//funciona
exports.actualizarRutina = function(req, res) {
    Rutina.update({nombre : req.params.nombre}, req.body, { new: true }, function(err, data) {
          if (err)
            res.send(err);
        res.json(data);
		console.log ( 'Rutina actualizada' ) ;
          });
};
//funciona
exports.actualizarEjercicio = function(req, res) {
    Ejercicio.update({id : req.params.id}, req.body, { new: true }, function(err, data) {
          if (err)
            res.send(err);
        res.json(data);
		console.log ( 'Ejercicio actualizado' ) ;
          });
};

