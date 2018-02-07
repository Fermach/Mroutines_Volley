var mongoose=require('mongoose');
var Schema= mongoose.Schema;

var modeloRutina= new Schema({
   nombre: {type: String, required:true ,unique: true},
   tipo: String,
   nivel:String
	});
	

var Rutina =mongoose.model('Rutinas',modeloRutina);
module.exports=Rutina;