package org.sistemas.com.bo.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.map.MarkerDragEvent;
import org.primefaces.event.map.OverlaySelectEvent;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.LatLng;
import org.primefaces.model.map.MapModel;
import org.primefaces.model.map.Marker;
import org.primefaces.model.map.Polygon;
import org.primefaces.model.map.Polyline;
import org.sistemas.com.bo.domain.Coordenada;
import org.sistemas.com.bo.domain.Coordenadai;
import org.sistemas.com.bo.service.UpdsService;

public class MapBeanP implements Serializable {  
	
	
	/*******************SERVICIOS*****************************/		
	private UpdsService updsService;	
	public void setUpdsService(UpdsService updsService)	
	{
		this.updsService = updsService;  		
		
	}	
	public MapBeanP() {    }  
	/*********************LISTA*************************/
	private List<Coordenada> coordenadas=new ArrayList<Coordenada>();	
	private List<Coordenada> coordenadasF;	
	
	public List<Coordenada> getCoordenadas() {
		return coordenadas;
	}
	public void setCoordenadas(List<Coordenada> coordenadas) {
		this.coordenadas = coordenadas;
	}	
    
    public List<Coordenada> getCoordenadasF() {
		return coordenadasF;
	}
	public void setCoordenadasF(List<Coordenada> coordenadasF) {
		this.coordenadasF = coordenadasF;
	}
	/***************************COORDENADA INICIAL*********************************/
    private Coordenadai coordenadaInicial;	 
    private double latitud;
	private double longitud;
	private int idi;
	
	public Coordenadai getCoordenadaInicial() {
		return coordenadaInicial;	}

	public void setCoordenadaInicial(Coordenadai coordenadaInicial) {
		this.coordenadaInicial = coordenadaInicial;
	}	
	public double getLatitud() {
		return latitud;
	}
	public void setLatitud(double latitud) {
		this.latitud = latitud;
	}
	public double getLongitud() {
		return longitud;
	}
	public void setLongitud(double longitud) {
		this.longitud = longitud;
	}
    
    public int getIdi() {
		return idi;
	}
	public void setIdi(int idi) {
		this.idi = idi;
	}
	/***************************PARA CREAR EMPTY MODEL*********************************/
    private MapModel emptyModel;
    private String title;     
    private double lat;       
    private double lng;  
    
    public MapModel getEmptyModel() {  
    	
    	
    	latitud=-19.047908186835446;
		longitud= -65.25962054729462;	
		emptyModel = new DefaultMapModel(); 
		
		List<Coordenadai> coordenadasi=new ArrayList<Coordenadai>();
    	coordenadasi=updsService.getCoordenadasip();
    	Coordenadai coordenada=new Coordenadai();
    	for(Iterator it=coordenadasi.iterator();it.hasNext();)		
		{
			coordenada=(Coordenadai)it.next();
			emptyModel.addOverlay(lineadibujar(coordenada.getId(),coordenada.getTitulo())); 
			
		}
        
		
        return emptyModel;  
    }  
    public String getTitle() {  
        return title;  
    }  
  
    public void setTitle(String title) {  
        this.title = title;  
    }  
  
    public double getLat() {  
        return lat;  
    }  
  
    public void setLat(double lat) {  
        this.lat = lat;  
    }  
  
    public double getLng() {  
        return lng;  
    }  
  
    public void setLng(double lng) {  
        this.lng = lng;  
    }  
    public void addMarker(ActionEvent actionEvent) {  
        Marker marker = new Marker(new LatLng(lat, lng), title);  
        Coordenadai coord=new Coordenadai(lat,lng,title,"poligono",0,"","","","","","");
        updsService.insertCoordenadai(coord);
        
        Coordenadai coordi=updsService.getCoordenadaiMover(coord);
        
        coordenadaInicial=updsService.getCoordenadai(coordi.getId());
		latitud=coordenadaInicial.getLat();
		longitud= coordenadaInicial.getLon();
		idi=coordenadaInicial.getId();
		
		descr="";
	  	 cod="";
	  	 fam="";
	  	 dim="";
	  	 est="";
	  	 pro="";
	  	 activador="true";
	  	 
        emptyModel.addOverlay(marker);   
        crear(null);
        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Added", "Lat:" + lat + ", Lng:" + lng));  
    }  
    public void crear(ActionEvent actionEvent)
    {   
    	
        draggableModel = new DefaultMapModel(); 
        coordenadaInicial=updsService.getCoordenadai(idi);
        selectedCar=coordenadaInicial;
        System.out.println("seleccionado"+selectedCar.getId());
        draggableModel = new DefaultMapModel(); 
        coordenadaInicial=updsService.getCoordenadai(selectedCar.getId());
	    updsService.insertCoordenada(new Coordenada(coordenadaInicial.getLat(),coordenadaInicial.getLon(),coordenadaInicial.getTitulo(),coordenadaInicial.getId()));
	    latitud=coordenadaInicial.getLat();
	    longitud= coordenadaInicial.getLon();
    	Marker markera = new Marker(new LatLng(coordenadaInicial.getLat(), coordenadaInicial.getLon()), coordenadaInicial.getTitulo());  
    	draggableModel.addOverlay(markera);
    	
    	
       	List<Coordenadai> coordenadasi=new ArrayList<Coordenadai>();
    	coordenadasi=updsService.getCoordenadasip();
    	Coordenadai coordenada=new Coordenadai();
    	for(Iterator it=coordenadasi.iterator();it.hasNext();)		
		{
    		
			coordenada=(Coordenadai)it.next();
    		
    			draggableModel.addOverlay(lineadibujar(coordenada.getId(),coordenada.getTitulo())); 
    		
			
			
		}
    	
    	for(Marker marker : draggableModel.getMarkers()) {  
            marker.setDraggable(true);  
        }  
    	cars=updsService.getCoordenadasip();
   	 mediumCarsModel = new CarDataModel(cars); 
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation
    	(FacesContext.getCurrentInstance(), "null", (new StringBuilder
    			("/paginas/bancos/facelets/faceletsMoverP.xhtml?")).append("faces-redirect=true").toString());
			
    }
    /***************************PARA MOVER DRAGGABLE MODEL*********************************/
    private MapModel draggableModel; 
    public MapModel getDraggableModel() {  
        return draggableModel;  
    } 
    
    
    
    public void mover(ActionEvent actionEvent)
    {   
		
		draggableModel = new DefaultMapModel(); 
		latitud=-19.047908186835446;
		longitud= -65.25962054729462;
		
		descr="";
   	 cod="";
   	 fam="";
   	 dim="";
   	 est="";
   	 pro="";
   	 activador="true";
   	 
	 
    	List<Coordenadai> coordenadasi=new ArrayList<Coordenadai>();
    	coordenadasi=updsService.getCoordenadasip();
    	
    	Coordenadai coordenada=new Coordenadai();
    	for(Iterator it=coordenadasi.iterator();it.hasNext();)		
		{
			coordenada=(Coordenadai)it.next();
			draggableModel.addOverlay(lineadibujar(coordenada.getId(),coordenada.getTitulo())); 
			
		}
      
    	for(Marker marker : draggableModel.getMarkers()) {  
            marker.setDraggable(true);  
        }  
    	cars=updsService.getCoordenadasip();
    	 mediumCarsModel = new CarDataModel(cars); 
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation
    	(FacesContext.getCurrentInstance(), "null", (new StringBuilder
    			("/paginas/bancos/facelets/faceletsMoverP.xhtml?")).append("faces-redirect=true").toString());
			
    }
    public Polygon lineadibujar(int id, String color)
    {
    	//Polyline polyline = new Polyline(); 
    	Polygon polyline = new Polygon();  
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
        if(color.equals("Amarillo"))
        {
        	polyline.setStrokeColor("#fffc00");
        	polyline.setFillColor("#fffc00"); 
        }
        if(color.equals("Rojo"))
        {
        	polyline.setStrokeColor("#ff0000");
        	polyline.setFillColor("#ff0000"); 
        }
        if(color.equals("Azul"))
        {
        	polyline.setStrokeColor("#001eff");
        	polyline.setFillColor("#001eff"); 
        }
        if(color.equals("Verde"))
        {
        	polyline.setStrokeColor("#5aff00");
        	polyline.setFillColor("#5aff00"); 
        }
        if(color.equals("Celeste"))
        {
        	polyline.setStrokeColor("#00f5fe");
        	polyline.setFillColor("#00f5fe"); 
        }
         
          
        //polyline.setStrokeOpacity(0.7); 
        polyline.setFillOpacity(0.5);  
        
    	
    	return polyline;
    }
    
    public void onMarkerDrag(MarkerDragEvent event) {  
    	Marker  marker1 = event.getMarker();  
    	 
    	draggableModel = new DefaultMapModel(); 
    	
    	coordenadaInicial.setLat(marker1.getLatlng().getLat());
    	coordenadaInicial.setLon(marker1.getLatlng().getLng());
    	updsService.updateCoordenadai(coordenadaInicial);
    	
    	Coordenada coord=new Coordenada(marker1.getLatlng().getLat(),marker1.getLatlng().getLng(),"",coordenadaInicial.getId());
        updsService.insertCoordenada(coord);
        
        Marker markera = new Marker(new LatLng(coordenadaInicial.getLat(), coordenadaInicial.getLon()),  "Karaalioglu Parki", "karaalioglu.png", "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png");
    	//draggableModel.addOverlay(new Marker(new LatLng(coordenadaInicial.getLat(), coordenadaInicial.getLon()),  "Karaalioglu Parki", "karaalioglu.png", "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png"));

    	draggableModel.addOverlay(markera);
    	
    	List<Coordenadai> coordenadasi=new ArrayList<Coordenadai>();
    	coordenadasi=updsService.getCoordenadasip();
    	Coordenadai coordenada=new Coordenadai();
    	for(Iterator it=coordenadasi.iterator();it.hasNext();)		
		{
			coordenada=(Coordenadai)it.next();
			draggableModel.addOverlay(lineadibujar(coordenada.getId(),coordenada.getTitulo())); 
			
		}
    	
        //draggableModel.addOverlay(lineadibujar(selectedCar.getId(),selectedCar.getTitulo())); 
        
        for(Marker marker : draggableModel.getMarkers()) {  
            marker.setDraggable(true);  
        } 
        descr="";
     	 cod="";
     	 fam="";
     	 dim="";
     	 est="";
     	 pro="";
     	 activador="true";
        FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation
    	(FacesContext.getCurrentInstance(), "null", (new StringBuilder
    			("/paginas/bancos/facelets/faceletsMoverP.xhtml?")).append("faces-redirect=true").toString());
        
        
        //addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + marker.getLatlng().getLat() + ", Lng:" + marker.getLatlng().getLng()));
        //addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Marker Dragged", "Lat:" + event. + ", Lng:" + marker.getLatlng().getLng()));  
    }      
    /***************************PARA DIBUJAR LINEA POLYLINE MODEL*********************************/
    private MapModel polylineModel;  
	 
	public MapModel getPolylineModel() {  
	        return polylineModel;  
	}  
	  
	public void onPolylineSelect(OverlaySelectEvent event) {  
		//MapModel p = new DefaultMapModel();
		 Polygon polygon = new Polygon();  
		//p=(MapModel)event.getOverlay();
		polygon=(Polygon)event.getOverlay();
		
		List<LatLng> puntos=new ArrayList<LatLng>();
		double a=0;
		double b=0;
		
		puntos=polygon.getPaths();
		LatLng coord1 = new LatLng(36.879466, 30.667648); 
		for(Iterator it=puntos.iterator();it.hasNext();)		
    	{
			coord1=(LatLng)it.next();
			System.out.println("poly "+coord1.getLat()+" - "+coord1.getLng());
			a=coord1.getLat();
			b=coord1.getLng();
    	}
		Coordenadai coord=new Coordenadai(a,b,"");
		Coordenadai coord2=new Coordenadai();
		coord2=updsService.getCoordenadaiMover(coord);
		System.out.println("nombre "+coord2.getTipo()+ " - "+ coord2.getTitulo()  + "- id "+coord2.getId());
		
	        addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, "Polylineooo Selected", null));  
	}  
	public void lineas(ActionEvent actionEvent)
    {   
		polylineModel = new DefaultMapModel(); 
        Polyline polyline = new Polyline();  
        Coordenada coordenada=new Coordenada();
        coordenadas=updsService.getCoordenadas();
        for(Iterator it=coordenadas.iterator();it.hasNext();)		
    	{
        	coordenada=(Coordenada)it.next();
        	 polyline.getPaths().add(new LatLng(coordenada.getLat(), coordenada.getLon()));  
        	 
        	 Marker marker = new Marker(new LatLng(coordenada.getLat(), coordenada.getLon()), coordenada.getTitulo());  
        	 polylineModel.addOverlay(marker);
    	}         
        polyline.setStrokeWeight(5);  
        polyline.setStrokeColor("#ff0000");  
        polyline.setStrokeOpacity(0.9);  
          
        polylineModel.addOverlay(polyline);  
        
        latitud=coordenadaInicial.getLat();
	    longitud= coordenadaInicial.getLon();
		
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation
    	(FacesContext.getCurrentInstance(), "null", (new StringBuilder
    			("/paginas/bancos/facelets/faceletsLineas.xhtml?")).append("faces-redirect=true").toString());
			
    }
	/***************************LIMPIAR*********************************/
	public void limpiar(ActionEvent actionEvent)
    {   
		
        Coordenada coordenada=new Coordenada();
        coordenadas=updsService.getCoordenadas();
        for(Iterator it=coordenadas.iterator();it.hasNext();)		
    	{
        	coordenada=(Coordenada)it.next();
        	updsService.deleteCoordenada(coordenada);        	
    	}
        
        Coordenada coord=new Coordenada(latitud,longitud,"inicio",idi);
        updsService.insertCoordenada(coord);
          
        emptyModel = new DefaultMapModel();
        lineas(null);
			
    }	
    /************************************************************/
    
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
    
    /************************************** ATRIBUTOS **********************************************/
    private String descr="";
    
    private String cod="";
    private String fam="";
    private String dim="";
    private String est="";
    private String pro="";
    private String activador="true";
  
    public String getDescr() {
	return descr;
    }
    public void setDescr(String descr) {
	this.descr = descr;
    }

    
    public String getCod() {
		return cod;
	}
	public void setCod(String cod) {
		this.cod = cod;
	}
	public String getFam() {
		return fam;
	}
	public void setFam(String fam) {
		this.fam = fam;
	}
	public String getDim() {
		return dim;
	}
	public void setDim(String dim) {
		this.dim = dim;
	}
	public String getEst() {
		return est;
	}
	public void setEst(String est) {
		this.est = est;
	}
	public String getPro() {
		return pro;
	}
	public void setPro(String pro) {
		this.pro = pro;
	}
	
	public String getActivador() {
		return activador;
	}
	public void setActivador(String activador) {
		this.activador = activador;
	}
	public void update(ActionEvent actionEvent) {
	selectedCar.setDescr(descr);
	selectedCar.setCod(cod);
	selectedCar.setFam(fam);
	selectedCar.setDim(dim);
	selectedCar.setEst(est);
	selectedCar.setPro(pro);
	
	updsService.updateCoordenadai(selectedCar);
	
	descr="";
  	 cod="";
  	 fam="";
  	 dim="";
  	 est="";
  	 pro="";
  	 activador="true";
	FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation
	(FacesContext.getCurrentInstance(), "null", (new StringBuilder
			("/paginas/bancos/facelets/faceletsMoverP.xhtml?")).append("faces-redirect=true").toString());
	
}

/************************************** ATRIBUTOS **********************************************/
	public void setSelectedCar(Coordenadai selectedCar) {  
    	
        this.selectedCar = selectedCar;  
        
        if(accion.equals("editar"))
        {
        	
        	System.out.println("seleccionado"+selectedCar.getId());
            draggableModel = new DefaultMapModel(); 
            coordenadaInicial=updsService.getCoordenadai(selectedCar.getId());
    	    updsService.insertCoordenada(new Coordenada(coordenadaInicial.getLat(),coordenadaInicial.getLon(),coordenadaInicial.getTitulo(),coordenadaInicial.getId()));
    	    latitud=coordenadaInicial.getLat();
    	    longitud= coordenadaInicial.getLon();
        	Marker markera = new Marker(new LatLng(coordenadaInicial.getLat(), coordenadaInicial.getLon()), coordenadaInicial.getTitulo());  
        	//draggableModel.addOverlay(markera);
        	draggableModel.addOverlay(new Marker(new LatLng(coordenadaInicial.getLat(), coordenadaInicial.getLon()),  "Karaalioglu Parki", "karaalioglu.png", "http://maps.google.com/mapfiles/ms/micons/yellow-dot.png"));
        	
        	activador="false";
        	 descr=selectedCar.getDescr();
        	 cod=selectedCar.getCod();
        	 fam=selectedCar.getFam();
        	 dim=selectedCar.getDim();
        	 est=selectedCar.getEst();
        	 pro=selectedCar.getPro();
           	List<Coordenadai> coordenadasi=new ArrayList<Coordenadai>();
        	coordenadasi=updsService.getCoordenadasip();
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
        	cars=updsService.getCoordenadasip();
       	 mediumCarsModel = new CarDataModel(cars); 
       	 
       	updsService.deleteCoordenada(new Coordenada(2,3,"",selectedCar.getId()));
       	
       	draggableModel = new DefaultMapModel(); 
       	List<Coordenadai> coordenadasi=new ArrayList<Coordenadai>();
    	coordenadasi=updsService.getCoordenadasip();
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
    			("/paginas/bancos/facelets/faceletsMoverP.xhtml?")).append("faces-redirect=true").toString());
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
    	
    	descr="";
    	 cod="";
    	 fam="";
    	 dim="";
    	 est="";
    	 pro="";
    	 activador="true";
    	
		FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation
    	(FacesContext.getCurrentInstance(), "null", (new StringBuilder
    			("/paginas/bancos/facelets/faceletsMoverP.xhtml?")).append("faces-redirect=true").toString());
	}
  
    
  
}  
