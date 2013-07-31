package com.shafer.mediastreamer.server;


public class NetworkDiscovery extends Thread {


	public NetworkDiscovery(String serverAddress, int serverPort) {	

	}
	public void initializeRegistrationListener() {
	    	mRegistrationListener = new NsdManager.RegistrationListener() {
	
	        @Override
	        public void onServiceRegistered(NsdServiceInfo NsdServiceInfo) {
	            // Save the service name.  Android may have changed it in order to
	            // resolve a conflict, so update the name you initially requested
	            // with the name Android actually used.
	            mServiceName = NsdServiceInfo.getServiceName();
	        }
	
	        @Override
	        public void onRegistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
	            // Registration failed!  Put debugging code here to determine why.
	        }
	
	        @Override
	        public void onServiceUnregistered(NsdServiceInfo arg0) {
	            // Service has been unregistered.  This only happens when you call
	            // NsdManager.unregisterService() and pass in this listener.
	        }
	
	        @Override
	        public void onUnregistrationFailed(NsdServiceInfo serviceInfo, int errorCode) {
	            // Unregistration failed.  Put debugging code here to determine why.
	        }
	    };
	}
	public void registerService(int port) {
	    NsdServiceInfo serviceInfo  = new NsdServiceInfo();
	    serviceInfo.setServiceName("NsdChat");
	    serviceInfo.setServiceType("_http._tcp.");
	    serviceInfo.setPort(port);
	
	    mNsdManager = Context.getSystemService(Context.NSD_SERVICE);
	
	    mNsdManager.registerService( serviceInfo, NsdManager.PROTOCOL_DNS_SD, mRegistrationListener);
	}
	public void run() {

	}
}
