package com.system.Sistemadeviajes.models;

public class ClienEmpleModel {
         private ClienteModel cliente;
         private EmpleadoModel empleado;
		
         public ClienEmpleModel(ClienteModel cliente, EmpleadoModel empleado) {
			super();
			this.cliente = cliente;
			this.empleado = empleado;
		}

		public ClienteModel getCliente() {
			return cliente;
		}

		public void setCliente(ClienteModel cliente) {
			this.cliente = cliente;
		}

		public EmpleadoModel getEmpleado() {
			return empleado;
		}

		public void setEmpleado(EmpleadoModel empleado) {
			this.empleado = empleado;
		}
         
         
}//Fin class
