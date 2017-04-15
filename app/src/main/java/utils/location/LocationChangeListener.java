package utils.location;



public interface LocationChangeListener {
	
	public void onLocationChange();
	public void onLocationSucess();
	public void onLocationFailure(String message);
	public void onProviderEnable();

}
