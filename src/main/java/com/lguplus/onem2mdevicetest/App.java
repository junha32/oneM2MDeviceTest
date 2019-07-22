package com.lguplus.onem2mdevicetest;

import java.math.BigInteger;

import com.lguplus.onem2m.device.Onem2mAgent;
import com.lguplus.onem2m.device.OperationListener;
import com.lguplus.onem2m.device.attributes.domain.AEAttributes;
import com.lguplus.onem2m.device.attributes.domain.Attributes;
import com.lguplus.onem2m.device.attributes.domain.ConditionalRequest;
import com.lguplus.onem2m.device.attributes.domain.ContainerAttributes;
import com.lguplus.onem2m.device.attributes.domain.RemoteCSEAttributes;
import com.lguplus.onem2m.device.common.exception.IotpException;
import com.lguplus.onem2m.device.resources.domain.AE;
import com.lguplus.onem2m.device.resources.domain.CSEBase;
import com.lguplus.onem2m.device.resources.domain.Container;
import com.lguplus.onem2m.device.resources.domain.RemoteCSE;
import com.lguplus.onem2m.device.resources.domain.Resource;
import com.lguplus.onem2m.mef.connector.domain.auth.request.DeviceInfo;

/**
 * Hello world!
 *
 */
public class App implements OperationListener {
	
	Onem2mAgent agent;

	public static void main( String[] args ) {
		new App().start();
	}

	public App() {
		agent = new Onem2mAgent();
	}

	public void start() {
		// Initialize
		try {
			agent.initilaize( "src/main/resources/configuration.xml" );
		} catch ( IotpException e ) {
			System.out.println( "Error. Error Code : " + e.getCode() + ", message : " + e.getMessage() );
		}

		// Authenticate
		try {
			DeviceInfo deviceInfo = new DeviceInfo();

			deviceInfo.setDeviceModel( "PMV-DEVICE-TEST" );
			deviceInfo.setServiceCode( "PMVT" );
			deviceInfo.setDeviceType( "asn" ); // adn : "AE" or mn : "RemoteCSE"
			deviceInfo.setCtn( "01007080001" );
			deviceInfo.setMacAddress( "" );
			deviceInfo.setDeviceSerialNo( "20190708000000000001" );
			deviceInfo.setIccId( "000001" );

			agent.authenticate( deviceInfo );
			String EntityId = agent.getEntityId();
		} catch ( IotpException e ) {
			System.out.println( "Error. Errmedor Code : " + e.getCode() + ", message : " + e.getMessage() );
		}

		// Retrieve CSEBase
		try {
			CSEBase cse = (CSEBase)agent.retrieve( "/IN_CSE-BASE-1/cb-1" );
			System.out.println( "CSE resource name: " + cse.getResourceName() );
		} catch ( IotpException e ) {
			System.out.println( "Error. Error Code : " + e.getCode() + ", message : " + e.getMessage() );
		}
		
//		// Create RemoteCSE
//		
//		try {
//			RemoteCSEAttributes attributes = new RemoteCSEAttributes();
//			attributes.setResourceName("csr-ASN_CSEDa73fbf5512PMVT");
//			attributes.setCseType(new BigInteger("3"));
//			attributes.setCSEBase("/ASN_CSE-D-a73fbf5512PMVT/cb-1");
//			attributes.setCSEID("/ASN_CSE-D-a73fbf5512PMVT");
//			attributes.setRequestReachability(true);
//			
//			
//			RemoteCSE remotecse = (RemoteCSE)agent.create(attributes, "/IN_CSE-BASE-1/cb-1");
//			System.out.println( "RemoteCSE resource ID: " + remotecse.getResourceID() );
//			
//		} catch ( IotpException e ) {
//			System.out.println( "Error. Error Code : " + e.getCode() + ", message : " + e.getMessage() );
//		}
		
		// Retrieve RemoteCSE
		
		try {
			
			RemoteCSE remotecse = (RemoteCSE)agent.retrieve("/IN_CSE-BASE-1/cb-1/csr-ASN_CSEDa73fbf5512PMVT");
			System.out.println( "RemoteCSE resource ID: " + remotecse.getResourceID() );
			
		} catch ( IotpException e ) {
			System.out.println( "Error. Error Code : " + e.getCode() + ", message : " + e.getMessage() );
		}

//		// Create Container
//		
//		try {
//			ContainerAttributes attributes = new ContainerAttributes();
//			attributes.setResourceName("cnt-ASN_CSEDa73fbf5512PMVT");
//			attributes.setMaxNrOfInstances(new Long("3"));
//			attributes.setMaxByteSize(new Long("4000"));
//			
//			Container container = (Container)agent.create(attributes, "/IN_CSE-BASE-1/cb-1/csr-ASN_CSEDa73fbf5512PMVT");
//			System.out.println( "Container resource ID: " + container.getResourceID() );
//			
//		} catch ( IotpException e ) {
//			System.out.println( "Error. Error Code : " + e.getCode() + ", message : " + e.getMessage() );
//		}
		
//		// Update Container
//		try {
//			ContainerAttributes attributes = new ContainerAttributes();
//			attributes.setResourceName("csn-ASN_CSEDa73fbf5512PMVT");
//			Container container = (Container)agent.update( attributes, "/IN_CSE-BASE-1/cb-1/ri_cnt-a9a69d1938234cf8a85339f8508d829a" );
//		} catch ( IotpException e ) {
//			System.out.println( "Error. Error Code : " + e.getCode() + ", message : " + e.getMessage() );
//		}
		
		// Retrieve Container
		
		try {
			Container container = (Container)agent.retrieve("/IN_CSE-BASE-1/cb-1/csr-ASN_CSEDa73fbf5512PMVT/cnt-ASN_CSEDa73fbf5512PMVT");
			System.out.println( "Container resource ID: " + container.getResourceID() );
			
		} catch ( IotpException e ) {
			System.out.println( "Error. Error Code : " + e.getCode() + ", message : " + e.getMessage() );
		}
		
		
//		// Create AE
//		try {
//			AEAttributes attributes = new AEAttributes();
//			attributes.setResourceName( "ae-ASN_CSEDa73fbf5512PMVT" );
//			attributes.getLabels().add( "example" );
//			attributes.setAppID("api-ae-ASN_CSEDa73fbf5512PMVT");
//			attributes.setRequestReachability( true );
//			attributes.setAppName("PMVT");
//
//			AE ae = (AE)agent.create( attributes, "/IN_CSE-BASE-1/cb-1/csr-ASN_CSEDa73fbf5512PMVT" );
//			System.out.println( "CSE resource ID: " + ae.getResourceID() );
//		} catch ( IotpException e ) {
//			System.out.println( "Error. Error Code : " + e.getCode() + ", message : " + e.getMessage() );
//		}
//
//		// Update AE
//		try {
//			AEAttributes attributes = new AEAttributes();
//			attributes.setAppName( "example" );
//			AE ae = (AE)agent.update( attributes, "/IN_CSE-BASE-1/cb-1/ae-ae_resource_name" );
//		} catch ( IotpException e ) {
//			System.out.println( "Error. Error Code : " + e.getCode() + ", message : " + e.getMessage() );
//		}
//
//		// Retrieve AE
//		try {
//			AE ae = (AE)agent.retrieve( "/IN_CSE-BASE-1/cb-1/ae-ae_resource_name" );
//			System.out.println( "AE App Name : " + ae.getAppName() );
//		} catch ( IotpException e ) {
//			System.out.println( "Error. Error Code : " + e.getCode() + ", message : " + e.getMessage() );
//		}
//
//		// Delete AE
//		try {
//			AE ae = (AE)agent.delete( "/IN_CSE-BASE-1/cb-1/ae-ae_resource_name" );
//		} catch ( IotpException e ) {
//			System.out.println( "Error. Error Code : " + e.getCode() + ", message : " + e.getMessage() );
//		}
//
//		// check AE is deleted
//		try {
//			agent.retrieve( "/IN_CSE-BASE-1/cb-1/ae-ae_resource_name" );
//		} catch ( IotpException e ) {
//			if ( e.getCode() == 4004 ) {
//				System.out.println( "AE is deleted" );
//			}
//
//		}

		// Finalize
		try {
			agent.finalize();
		} catch ( IotpException e ) {
			System.out.println( "Error. Error Code : " + e.getCode() + ", message : " + e.getMessage() );
		}
	}

	public Resource onRequestCreate( Attributes attributes, String from, String to ) throws IotpException {
		System.out.println( "onRequestCreate" );
		// if return Resource, null, it means 'to create is succeeded'.
		// if throw IotpException, it means 'to create is failed'
		return null;
	}

	public Resource onRequestRetrieve( String from, String to, ConditionalRequest condRequest ) throws IotpException {
		System.out.println( "onRequestRetrieve" );
		// if return Resource, null, it means 'to retrieve is succeeded'.
		// if throw IotpException, it means 'to retrieve is failed'
		return null;
	}

	public Resource onRequestUpdate( Attributes attributes, String from, String to, ConditionalRequest condRequest ) throws IotpException {
		System.out.println( "onRequestUpdate" );
		// if return Resource, null, it means 'to update is succeeded'.
		// if throw IotpException, it means 'to update is failed'
		return null;
	}

	public Resource onRequestDelete( String from, String to, ConditionalRequest condRequest ) throws IotpException {
		System.out.println( "onRequestDelete" );
		// if return Resource, null, it means 'to create is succeeded'.
		// if throw IotpException, it means 'to create is failed'
		return null;
	}

	public void onNotify( Attributes attributes, Resource resource, String from, String to ) throws IotpException {
		System.out.println( "onNotify" );
		// attributes is instance of NotificationAttributes.
		// resource is instance of changed resource which has subscription
	}

    
}
