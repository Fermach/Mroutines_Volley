var mongoose=require('mongoose');
var Schema= mongoose.Schema;

var modeloEjercicio= new Schema({
     id:{type:String,required :true, unique:true}, 
	 nombre: {type: String, required:true},
     series: {type: Number, default: 0,},
     repeticiones: {type: Number, default: 0},
     tiempo: {type: String, default: 0},
     tipo: {type: String, default:''},
     rutina: {type: String, required: true}   
  
	});
	

var Ejercicio= mongoose.model('Ejercicios', modeloEjercicio);
module.exports=Ejercicio;