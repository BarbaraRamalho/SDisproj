package pt.upa.broker.ws;
import javax.jws.WebService;
import javax.xml.ws.Endpoint;



import java.util.*;
import java.lang.Iterable;
import java.util.Iterator;
import java.lang.*;





@WebService(
    endpointInterface="pt.upa.broker.ws.BrokerPortType",
    wsdlLocation="broker.1_0.wsdl",
    name="BrokerService",
    portName="BrokerPort",
    targetNamespace="http://ws.broker.upa.pt/",
    serviceName="BrokerService"
)
public class BrokerPort {

	
String[] location = {"Porto","Braga","Viana do castelo"};
List lst = Arrays.asList(location);



   /*ping copiado nao sei se funciona*/ 
    public String ping(String name){
        Ping p=new Ping();
         p.setName(name);
        
        return "p.getReturn()";
    }

            
    public String requestTransport(String origin,String destination,int price) throws InvalidPriceFault_Exception, UnavailableTransportFault_Exception, 
        UnavailableTransportPriceFault_Exception, UnknownLocationFault_Exception{

            Iterator itr= lst.iterator();
            if (price==0)
                throw new InvalidPriceFault_Exception("no such prive", new InvalidPriceFault());
            do{
                if((itr.next().equals(origin))==false)
                        throw new UnknownLocationFault_Exception("origem desconhecida",new UnknownLocationFault());
                if((itr.next().equals(destination))==false)
                        throw new UnknownLocationFault_Exception("destino desconhecido", new UnknownLocationFault());
            }while(itr.hasNext());
            
            return "marcado";
    }

    public TransportView viewTransport(String id) throws UnknownTransportFault_Exception{
            //contactar a transportador para ver o estado do transporte. tem que devolver TransportView
        ViewTransportResponse ret=new ViewTransportResponse();
        return ret.getReturn();
    }
    
    public List<TransportView> listTransports(){

        //esta lista deve ser feita com um id de transporte id.getList();
       
       // ListTransports lst=Arrays.asList(new ListTransports());
        List<TransportView> trpVew = new ArrayList();
        for(TransportView t:trpVew){
       		t.getState();
       	}
        return  trpVew;//precisa de devolver uma lista de estados mas primeiro tenho de criar uma lista de transvortes.
    }

    public void clearTransports(){
    	ClearTransports clT=new ClearTransports();
    	ClearTransportsResponse clTR = new ClearTransportsResponse();
       

    }


}
