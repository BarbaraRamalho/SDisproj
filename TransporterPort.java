
package pt.upa.transporter.ws;
import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.*;
//import java.lang.Iterable;
import java.util.Iterator;
import java.lang.*;





@WebService(
    endpointInterface="pt.upa.transporter.ws.TransporterPortType",
    wsdlLocation="transporter.1_0.wsdl",
    name="TransporterWebService",
    portName="TransporterPort",
    targetNamespace="http://ws.transporter.upa.pt/",
    serviceName="TransporterService"
)



public class TransporterPort implements TransporterPortType{

	// TODO
    //cosidere transp impar
    /*Regiao Centro: Lisboa, Leiria, Santarem, Castelo Branco, Coimbra, Aveiro, Viseu, Guarda;
      Regiao Sul; Setubal,Evora, Portalegre, Beja, Faro.*/

    String[] location = {"Lisboa", "Leiria", "Santarem", "Castelo Branco", "Coimbra", "Aveiro", "Viseu", "Guarda","Setubal","Evora", "Portalegre", "Beja", "Faro"};
    List lst = Arrays.asList(location);
    JobView job=new JobView();
  
	public String ping(String name){
        return "ola";
    }

    public JobView requestJob(String origin,String destination,int price)throws BadLocationFault_Exception, BadPriceFault_Exception{

          Iterator itr= lst.iterator();
          if (price<=0)
                throw new BadPriceFault_Exception("no such prive", new BadPriceFault());
         for(String l:location){
            if((l.equals(origin))==false)
                throw new BadLocationFault_Exception("origem desconhecida",new BadLocationFault());//deve devover null-corrigir conforme enun
            if((l.equals(destination))==false)
                throw new BadLocationFault_Exception("destino desconhecido", new BadLocationFault());
        }
        
        job.setCompanyName("nome");
        //job.setJobIdentifier("id_nome");
        job.setJobOrigin(origin);
        job.setJobDestination(destination);
        job.setJobPrice(price);
       // job.setJobState((JobStateView) "PROPOSED");

        return job;//adequar ao enun
    }
    
    public JobView decideJob(String id, boolean accept) throws BadJobFault_Exception{
           
            //DecideJob d = job.setAccept(accept);****************é preciso usar o decide job mas nao sei como
          //  this.setAccept(accept);

            return job;

        }
    public JobView jobStatus(String id){
        List<JobView> l= listJobs();
            for (JobView j: l){
                if((j.getJobIdentifier().equals(id))==false)
                    return null;
                //return j.JobStateView().fromVelue(id); resolver esta parte
            }
            return new JobView();

    }

    public List<JobView> listJobs(){
        List<JobView> lst= new ListJobsResponse().getReturn();
        


        return lst;


    }
    
    public void clearJobs(){
    }


}