package com.system.Sistemadeviajes.helpers;

public class ViewRouteHelpers {
	
	//redirects	
	public final static String ROUTE_INDEX = "/index";
	public final static String CLIENT_ROOT = "/clientes";
	public final static String EMPLOYEE_ROOT = "/empleados";
	public final static String TRAVEL_ROOT = "/viajes";
	
	//Home
	public final static String HOME = "home/home";
	public static final String DASHBOARD_INDEX = "/home/dashboard";

	
	//Cliente
	public final static String CLIENT_INDEX = "cliente/index";
	
	//Viajes
	public final static String TRAVEL_INDEX = "viaje/index";
	public final static String TRAVEL_PEDIRFECHAS_CLI_EMPL = "viaje/pedirFechasCliEmpl";
	public final static String TRAVEL_CLI_EMPL_ENTRE_FECHAS = "viaje/viajesCliEmplEntreFechas";
	public final static String TRAVEL_PEDIRFECHAS_CLI = "viaje/pedirFechasCli";
	public final static String TRAVEL_PEDIRFECHAS_EMPLE = "viaje/pedirFechasEmple";
	public final static String TRAVEL_CLI_ENTRE_FECHAS = "viaje/viajesDeCliEntreFechas";
	public final static String TRAVEL_RES_EMPLE_ENTRE_FECHAS = "viaje/resumenEmpleEntreFachas";
	public final static String TRAVEL_P_D_CLIE_EMPL = "viaje/printDownloadCliEmpl";
	public final static String TRAVEL_P_D_CLIENTE = "viaje/printDownloadCliente";
	
	//Empleado
	public final static String EMPLOYEE_UPDATE = "empleado/update";
	public final static String EMPLOYEE_ADD = "empleado/new";
	public final static String EMPLOYEE_INDEX = "empleado/index";
	
}//Fin class
