package com.lguplus.onem2mdevicetest;

import com.lguplus.onem2m.device.Onem2mAgent;
import com.lguplus.onem2m.device.OperationListener;
import com.lguplus.onem2m.device.attributes.domain.AEAttributes;
import com.lguplus.onem2m.device.attributes.domain.Attributes;
import com.lguplus.onem2m.device.attributes.domain.ConditionalRequest;
import com.lguplus.onem2m.device.common.exception.IotpException;
import com.lguplus.onem2m.device.resources.domain.AE;
import com.lguplus.onem2m.device.resources.domain.CSEBase;
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
		} catch ( IotpException e ) {
			System.out.println( "Error. Error Code : " + e.getCode() + ", message : " + e.getMessage() );
		}

		// Retrieve CSEBase
		try {
			CSEBase cse = (CSEBase)agent.retrieve( "/IN_CSE-BASE-1/cb-1" );
			System.out.println( "CSE resource name: " + cse.getResourceName() );
		} catch ( IotpException e ) {
			System.out.println( "Error. Error Code : " + e.getCode() + ", message : " + e.getMessage() );
		}

		// Create AE
		try {
			AEAttributes attributes = new AEAttributes();
			attributes.setResourceName( "ae-ae_resource_name" );
			attributes.getLabels().add( "example" );
			attributes.setAppID( "app_ID" );
			attributes.setRequestReachability( true );

			attributes.setResourceName( "ae-ae_resource_name" );
			attributes.setAppID( "api-1" );

			AE ae = (AE)agent.create( attributes, "/IN_CSE-BASE-1/cb-1" );
			System.out.println( "CSE resource ID: " + ae.getResourceID() );
		} catch ( IotpException e ) {
			System.out.println( "Error. Error Code : " + e.getCode() + ", message : " + e.getMessage() );
		}

		// Update AE
		try {
			AEAttributes attributes = new AEAttributes();
			attributes.setAppName( "example" );
			AE ae = (AE)agent.update( attributes, "/IN_CSE-BASE-1/cb-1/ae-ae_resource_name" );
		} catch ( IotpException e ) {
			System.out.println( "Error. Error Code : " + e.getCode() + ", message : " + e.getMessage() );
		}

		// Retrieve AE
		try {
			AE ae = (AE)agent.retrieve( "/IN_CSE-BASE-1/cb-1/ae-ae_resource_name" );
			System.out.println( "AE App Name : " + ae.getAppName() );
		} catch ( IotpException e ) {
			System.out.println( "Error. Error Code : " + e.getCode() + ", message : " + e.getMessage() );
		}

		// Delete AE
		try {
			AE ae = (AE)agent.delete( "/IN_CSE-BASE-1/cb-1/ae-ae_resource_name" );
		} catch ( IotpException e ) {
			System.out.println( "Error. Error Code : " + e.getCode() + ", message : " + e.getMessage() );
		}

		// check AE is deleted
		try {
			agent.retrieve( "/IN_CSE-BASE-1/cb-1/ae-ae_resource_name" );
		} catch ( IotpException e ) {
			if ( e.getCode() == 4004 ) {
				System.out.println( "AE is deleted" );
			}

		}

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
