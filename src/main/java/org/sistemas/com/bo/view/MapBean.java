package org.sistemas.com.bo.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;  
import javax.faces.context.FacesContext;  
import javax.faces.event.ActionEvent; 

import org.sistemas.com.bo.domain.Coordenada;
import org.sistemas.com.bo.domain.Coordenadai;
import org.sistemas.com.bo.service.UpdsService;

import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.event.map.PointSelectEvent;
import org.primefaces.model.map.DefaultMapModel;  
import org.primefaces.model.map.LatLng;  
import org.primefaces.model.map.MapModel;

import org.primefaces.model.map.Circle;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.LatLngBounds;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;
import org.primefaces.model.map.Polyline;
import org.primefaces.model.map.Rectangle;
import org.primefaces.push.PushContext;
import org.primefaces.push.PushContextFactory;

import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
//import org.primefaces.examples.domain.Car;
import org.primefaces.model.SelectableDataModel;

public class MapBean  implements Serializable {  
	
	/*******************SERVICIOS*****************************/		
	private UpdsService updsService;	
	public void setUpdsService(UpdsService updsService)	{	this.updsService = updsService; 	}	
	public MapBean() {    }  
	/***************************ATRIBUTOS DE COORDENADA INICIAL*********************************/    
    private double lat;       
    private double lng; 
    private String title=""; // color
    private String tipo="linea"; // tipo de figura geometrica
    private String descr="NA";// descripcion
    private double r=0;
    private String cod=""; // linea de micro (1,5, etc. D,L etc.)
    private String fam=""; // micro, minibus, taxi 
    private String dim=""; // dimensiones
    private String est="Activo"; // estado 
    private String pro="Sin Definir"; // sindicato 
    private String activador="true";
    
    public String getTitle() {         return title;      }  
    public void setTitle(String title) {          this.title = title;      }    
    public double getLat() {          return lat;      }    
    public void setLat(double lat) {          this.lat = lat;      }    
    public double getLng() {          return lng;      }    
    public void setLng(double lng) {          this.lng = lng;      }      
    public double getR() {		return r;	}
    public void setR(double r) {		this.r = r;	}
	public String getDescr() {	return descr;    }
    public void setDescr(String descr) {	this.descr = descr;    }    
    public String getCod() {		return cod;	}
	public void setCod(String cod) {		this.cod = cod;	}
	public String getFam() {		return fam;	}
	public void setFam(String fam) {		this.fam = fam;	}
	public String getDim() {		return dim;	}
	public void setDim(String dim) {		this.dim = dim;	}
	public String getEst() {		return est;	}
	public void setEst(String est) {		this.est = est;	}
	public String getPro() {		return pro;	}
	public void setPro(String pro) {		this.pro = pro;	}	
	public String getActivador() {		return activador;	}
	public void setActivador(String activador) {		this.activador = activador;	}    
	public String getTipo() {		return tipo;	}
	public void setTipo(String tipo) {		this.tipo = tipo;	}
	/*********************************************************************/
	public void registro(ActionEvent actionEvent)
    {   	
		
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation
    	(FacesContext.getCurrentInstance(), "null", (new StringBuilder
    			("/paginas/bancos/facelets/faceletsBuscar.xhtml?")).append("faces-redirect=true").toString());			
    }
	
	public void verTrayectorias(ActionEvent actionEvent)
    {   
		
		draggableModel = new DefaultMapModel(); 
		latitud=-19.047908186835446;
		longitud= -65.25962054729462;	
	 
    	List<Coordenadai> coordenadasi=new ArrayList<Coordenadai>();
    	coordenadasi=updsService.getCoordenadasi();
    	
    	Coordenadai coordenada=new Coordenadai();
    	for(Iterator it=coordenadasi.iterator();it.hasNext();)		
		{
			coordenada=(Coordenadai)it.next();
			draggableModel.addOverlay(lineadibujar(coordenada.getId(),coordenada.getTitulo())); 
			
		}
    	
    	/*for(Marker marker : draggableModel.getMarkers()) {  
            marker.setDraggable(true);  
        }  */
    	cars=updsService.getCoordenadasi();
    	 mediumCarsModel = new CarDataModel(cars); 
    	 
    	 
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation
    	(FacesContext.getCurrentInstance(), "null", (new StringBuilder
    			("/paginas/bancos/facelets/faceletsMover.xhtml?")).append("faces-redirect=true").toString());
			
    }
	
	/*********************LISTA*************************/
	private List<Coordenada> coordenadas=new ArrayList<Coordenada>();	
	public List<Coordenada> getCoordenadas() {		return coordenadas;	}
	public void setCoordenadas(List<Coordenada> coordenadas) {		this.coordenadas = coordenadas;	}	
    
    /***************************COORDENADA INICIAL*********************************/
    private Coordenadai coordenadaInicial;	 
    private double latitud;
	private double longitud;
	private int idi;
	
	public Coordenadai getCoordenadaInicial() {		return coordenadaInicial;	}
	public void setCoordenadaInicial(Coordenadai coordenadaInicial) {		this.coordenadaInicial = coordenadaInicial;}	
	public double getLatitud() {		return latitud;	}
	public void setLatitud(double latitud) {		this.latitud = latitud;	}
	public double getLongitud() {		return longitud;	}
	public void setLongitud(double longitud) {		this.longitud = longitud;	}    
    public int getIdi() {		return idi;	}
	public void setIdi(int idi) {		this.idi = idi;	}
	
	/***************************PARA CREAR EMPTY MODEL*********************************/
	    
    public Polyline lineadibujar(int id, String color)
    {
    	Polyline polyline = new Polyline(); 
    	
    	Coordenada coordenada=new Coordenada();
        coordenadas=updsService.getCoordenadas();
        for(Iterator it=coordenadas.iterator();it.hasNext();)		
    	{
        	coordenada=(Coordenada)it.next();
        	if(coordenada.getIdi()==id)
        	{
        		polyline.getPaths().add(new LatLng(coordenada.getLat(), coordenada.getLon())); 
        	}        	
    	}           
        polyline.setStrokeWeight(3); 
        if(color.equals("Amarillo"))        {        	polyline.setStrokeColor("#fffc00");        }
        if(color.equals("Rojo"))        {        	polyline.setStrokeColor("#ff0000");        }
        if(color.equals("Azul"))        {        	polyline.setStrokeColor("#001eff");        }
        if(color.equals("Verde"))        {        	polyline.setStrokeColor("#5aff00");        }
        if(color.equals("Celeste"))        {        	polyline.setStrokeColor("#00f5fe");        }
         
    	return polyline;
    }
    /***************************************************************/
    private MapModel emptyModel;    
    
    public MapModel getEmptyModel() {    	
    	latitud=-19.047908186835446;
		longitud= -65.25962054729462;	
		emptyModel = new DefaultMapModel(); 
		
		List<Coordenadai> coordenadasi=new ArrayList<Coordenadai>();
    	coordenadasi=updsService.getCoordenadasi();
    	Coordenadai coordenada=new Coordenadai();
    	for(Iterator it=coordenadasi.iterator();it.hasNext();)		
		{
			coordenada=(Coordenadai)it.next();
			emptyModel.addOverlay(lineadibujar(coordenada.getId(),coordenada.getTitulo())); 			
		}		
        return emptyModel;  
    }  
       
    public void addMarker(ActionEvent actionEvent) {  
        Marker marker = new Marker(new LatLng(lat, lng), title); 
        // (lat,lon,titulo,tipo,d,descr,cod,fam,dim, est, pro)
        Coordenadai coord=new Coordenadai(lat,lng,title,tipo,0,descr,cod,fam,dim,est,pro);
        updsService.insertCoordenadai(coord);
        
        Coordenadai coordi=updsService.getCoordenadaiMover(coord);
        
        coordenadaInicial=updsService.getCoordenadai(coordi.getId());
		latitud=coordenadaInicial.getLat();
		longitud= coordenadaInicial.getLon();
		idi=coordenadaInicial.getId();
		
		
        emptyModel.addOverlay(marker);   
        crear(null);
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));  
    }  
    
    private MapModel draggableModel; 
    public MapModel getDraggableModel() {          return draggableModel;      } 
    
    public void crear(ActionEvent actionEvent)
    {       	
        draggableModel = new DefaultMapModel(); 
        coordenadaInicial=updsService.getCoordenadai(idi);
        selectedCar=coordenadaInicial;
        
	    updsService.insertCoordenada(new Coordenada(selectedCar.getLat(),selectedCar.getLon(),selectedCar.getTitulo(),selectedCar.getId()));
	    latitud=selectedCar.getLat();
	    longitud= selectedCar.getLon();
	    
    	Marker markera = new Marker(new LatLng(selectedCar.getLat(), selectedCar.getLon()), selectedCar.getTitulo());  
    	draggableModel.addOverlay(markera);
    	
    	
       	List<Coordenadai> coordenadasi=new ArrayList<Coordenadai>();
    	coordenadasi=updsService.getCoordenadasi();
    	Coordenadai coordenada=new Coordenadai();
    	for(Iterator it=coordenadasi.iterator();it.hasNext();)		
		{
    		
			coordenada=(Coordenadai)it.next();
    		
    			draggableModel.addOverlay(lineadibujar(coordenada.getId(),coordenada.getTitulo())); 
    		
			
			
		}
    	
    	for(Marker marker : draggableModel.getMarkers()) {  
            marker.setDraggable(true);  
        } 
    	cars=updsService.getCoordenadasi();
   	 mediumCarsModel = new CarDataModel(cars); 
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation
    	(FacesContext.getCurrentInstance(), "null", (new StringBuilder
    			("/paginas/bancos/facelets/faceletsMover.xhtml?")).append("faces-redirect=true").toString());
			
    }
    /***************************PARA MOVER DRAGGABLE MODEL*********************************/
   
    
    
    
    

    
    public void onMarkerDrag(MarkerDragEvent event) {  
    	Marker  marker = event.getMarker();  
    	 
    	draggableModel = new DefaultMapModel();
    	double km =distanciaCoord(coordenadaInicial.getLat(),coordenadaInicial.getLon(),marker.getLatlng().getLat(),marker.getLatlng().getLng());
    	
    	coordenadaInicial.setLat(marker.getLatlng().getLat());
    	coordenadaInicial.setLon(marker.getLatlng().getLng());
    	coordenadaInicial.setD(coordenadaInicial.getD()+km);
    	//coordenadaInicial.setDescr("descr");
    	updsService.updateCoordenadai(coordenadaInicial);
    	
    	//selectedCar=coordenadaInicial;
    	selectedCar.setLat(marker.getLatlng().getLat());
    	selectedCar.setLon(marker.getLatlng().getLng());
    	updsService.updateCoordenadai(selectedCar);
    	
    	Coordenada coord=new Coordenada(marker.getLatlng().getLat(),marker.getLatlng().getLng(),km+"",coordenadaInicial.getId());
        updsService.insertCoordenada(coord);
        
        Marker markera = new Marker(new LatLng(coordenadaInicial.getLat(), coordenadaInicial.getLon()),  "Karaalioglu Parki", "karaalioglu.png", "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png");

    	draggableModel.addOverlay(markera);
    	
    	 
    	 
    	List<Coordenadai> coordenadasi=new ArrayList<Coordenadai>();
    	coordenadasi=updsService.getCoordenadasi();
    	Coordenadai coordenada=new Coordenadai();
    	for(Iterator it=coordenadasi.iterator();it.hasNext();)		
		{
			coordenada=(Coordenadai)it.next();
			draggableModel.addOverlay(lineadibujar(coordenada.getId(),coordenada.getTitulo())); 
			
		}
    	
        //draggableModel.addOverlay(lineadibujar(selectedCar.getId(),selectedCar.getTitulo())); 
        
        for(Marker markern : draggableModel.getMarkers()) {  
            markern.setDraggable(true);  
        } 
        
        //draggableModel.addOverlay(lineadibujar(selectedCar.getId(),selectedCar.getTitulo())); 
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation
    	(FacesContext.getCurrentInstance(), "null", (new StringBuilder
    			("/paginas/bancos/facelets/faceletsMover.xhtml?")).append("faces-redirect=true").toString());
        
        //addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));
        //addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + event. + ", Lng:" + marker.getLatlng().getLng()));  
    }      
    private Marker markerkm;  
    
    
    public Marker getMarkerkm() {
		return markerkm;
	}
	public void setMarkerkm(Marker markerkm) {
		this.markerkm = markerkm;
	}
	public void onMarkerSelect(OverlaySelectEvent event) {  
    	Marker marker = (Marker) event.getOverlay();  
          
    	Coordenadai coord=new Coordenadai(marker.getLatlng().getLat(),marker.getLatlng().getLng(),"",tipo,0);
       
    	
        Coordenadai coordi=updsService.getCoordenadaiMover(coord);
        marker.setTitle("Km.: "+coordi.getD());
        marker.setData("mt.: "+coordi.getD()*1000);
        markerkm=marker;
    	addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Trayectoria", "Distancia en Km.:" + coordi.getD()));
    }  
    /***************************PARA DIBUJAR LINEA POLYLINE MODEL*********************************/
   
    public void addMessage(FacesMessage message) {  
        FacesContext.getCurrentInstance().addMessage(null, message);  
    }  
    
    
    /**************************************************************************************/
    private List<Coordenadai> cars;  
    
    private Coordenadai selectedCar;  
  
    private Coordenadai[] selectedCars;
    private CarDataModel mediumCarsModel;  
    
    private String accion;
    
    public Coordenadai[] getSelectedCars() {  
        return selectedCars;  
    }  
    public void setSelectedCars(Coordenadai[] selectedCars) {  
        this.selectedCars = selectedCars;  
    }  
  
    public Coordenadai getSelectedCar() {  
        return selectedCar;  
    }  
  
    public void setSelectedCar(Coordenadai selectedCar) {  
    	
        this.selectedCar = selectedCar;  
        if(accion.equals("editar"))
        {
        	System.out.println("seleccionado"+selectedCar.getId());
            draggableModel = new DefaultMapModel(); 
            
            coordenadaInicial=updsService.getCoordenadai(selectedCar.getId());
           
    	    //updsService.insertCoordenada(new Coordenada(coordenadaInicial.getLat(),coordenadaInicial.getLon(),coordenadaInicial.getTitulo(),coordenadaInicial.getId()));
    	    latitud=coordenadaInicial.getLat();
    	    longitud= coordenadaInicial.getLon();
        	Marker markera = new Marker(new LatLng(coordenadaInicial.getLat(), coordenadaInicial.getLon()), coordenadaInicial.getTitulo());  
        	draggableModel.addOverlay(markera);
        	//draggableModel.addOverlay(new Marker(new LatLng(coordenadaInicial.getLat(), coordenadaInicial.getLon()),  "Karaalioglu Parki", "karaalioglu.png", "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png"));
        	
        	activador="false";
        	descr=selectedCar.getTitulo();
       	 cod=selectedCar.getCod();
       	 fam=selectedCar.getFam();
       	 dim=selectedCar.getDim();
       	 est=selectedCar.getEst();
       	 pro=selectedCar.getPro();
       	 r=selectedCar.getD();
        	 
           	List<Coordenadai> coordenadasi=new ArrayList<Coordenadai>();
        	coordenadasi=updsService.getCoordenadasi();
        	Coordenadai coordenada=new Coordenadai();
        	for(Iterator it=coordenadasi.iterator();it.hasNext();)		
    		{
    			coordenada=(Coordenadai)it.next();
    			draggableModel.addOverlay(lineadibujar(coordenada.getId(),coordenada.getTitulo())); 
    			
    		}
        	
        	for(Marker marker : draggableModel.getMarkers()) {  
                marker.setDraggable(true);  
            }  
        }
        if(accion.equals("eliminar"))
        {
        	Coordenadai coordeliminar=new Coordenadai();
        	coordeliminar.setId(selectedCar.getId());
        	updsService.deleteCoordenadai(coordeliminar);
        	cars=updsService.getCoordenadasi();
       	 mediumCarsModel = new CarDataModel(cars); 
       	 
       	updsService.deleteCoordenada(new Coordenada(2,3,"",selectedCar.getId()));
       	
       	draggableModel = new DefaultMapModel(); 
       	List<Coordenadai> coordenadasi=new ArrayList<Coordenadai>();
    	coordenadasi=updsService.getCoordenadasi();
    	Coordenadai coordenada=new Coordenadai();
    	for(Iterator it=coordenadasi.iterator();it.hasNext();)		
		{
			coordenada=(Coordenadai)it.next();
			draggableModel.addOverlay(lineadibujar(coordenada.getId(),coordenada.getTitulo())); 
			
		}
    	System.out.println("elim");
       	 
        }
        
    	
    	FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation
    	(FacesContext.getCurrentInstance(), "null", (new StringBuilder
    			("/paginas/bancos/facelets/faceletsMover.xhtml?")).append("faces-redirect=true").toString());
    }
	public List<Coordenadai> getCars() {
		return cars;
	}
	public void setCars(List<Coordenadai> cars) {
		this.cars = cars;
	}
	public CarDataModel getMediumCarsModel() {
		return mediumCarsModel;
	}
	public void setMediumCarsModel(CarDataModel mediumCarsModel) {
		this.mediumCarsModel = mediumCarsModel;
	}  
	
	
	public String getAccion() {
		return accion;
	}
	public void setAccion(String accion) {
		this.accion = accion;
	}
	/*****************************/

	public void seleccionar(ActionEvent actionEvent)
	{
		
		draggableModel = new DefaultMapModel();
		System.out.println("holaa");
    	for(int i = 0; i < selectedCars.length; i++) {
            //options[i + 1] = new SelectItem(data[i], data[i]);
            System.out.println("dddd"+selectedCars[i].getTitulo()+ " - dfff -"+selectedCars[i].getId());
            draggableModel.addOverlay(lineadibujar(selectedCars[i].getId(),selectedCars[i].getTitulo()));
        }
    	
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation
    	(FacesContext.getCurrentInstance(), "null", (new StringBuilder
    			("/paginas/bancos/facelets/faceletsMover.xhtml?")).append("faces-redirect=true").toString());
	}
  
    /*************************distancia*************************/
  
	public static double distanciaCoord(double lat1, double lng1, double lat2, double lng2) {  
        //double radioTierra = 3958.75;//en millas  
        double radioTierra = 6371;//en kilómetros  
        double dLat = Math.toRadians(lat2 - lat1);  
        double dLng = Math.toRadians(lng2 - lng1);  
        double sindLat = Math.sin(dLat / 2);  
        double sindLng = Math.sin(dLng / 2);  
        double va1 = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)  
                * Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2));  
        double va2 = 2 * Math.atan2(Math.sqrt(va1), Math.sqrt(1 - va1));  
        double distancia = radioTierra * va2;  
   
        return distancia;  
    }  
	
	/*********************************************************************************************************/
	   
	public void update(ActionEvent actionEvent) {
	selectedCar.setTitulo(descr);
	selectedCar.setDescr(descr);
	selectedCar.setCod(cod);
	selectedCar.setFam(fam);
	selectedCar.setDim(dim);
	selectedCar.setEst(est);
	selectedCar.setPro(pro);
	selectedCar.setD(r);
	updsService.updateCoordenadai(selectedCar);
	

  	verTrayectorias(null);	
	}
	
	public void finalizarTrazo(ActionEvent actionEvent) {
		selectedCar.setTitulo(descr);
		selectedCar.setDescr(descr);
		selectedCar.setCod(cod);
		selectedCar.setFam(fam);
		selectedCar.setDim(dim);
		selectedCar.setEst(est);
		selectedCar.setPro(pro);
		selectedCar.setD(r);
		//updsService.updateCoordenadai(selectedCar);
		
		
	  	verTrayectorias(null);	
		}

	
	 
}