module.exports = function(app) {
  var controlador = require('./controlador');
  
  app.get('/', function(req,res){
  
     res.send('Servidor de Mroutines escuchando');
  })
  
  app.route('/rutinas')
    .get(controlador.listarRutinas)
    .post(controlador.crearRutina);

  app.route('/rutinas/nombre/:nombre')
    .get(controlador.listarUnaRutina)
    .delete(controlador.borrarUnaRutina)
    .put(controlador.actualizarRutina);
	
  app.route('/ejercicios')
    .get(controlador.listarEjercicios)
    .post(controlador.crearEjercicio);

  app.route('/ejercicios/id/:id')
    .delete(controlador.borrarUnEjercicio)
    .put(controlador.actualizarEjercicio);
	
  app.route('/ejercicios/rutina/:rutina')
    .get(controlador.listarEjerciciosPorRutina)
	.delete(controlador.borrarEjerciciosPorRutina)
    
}