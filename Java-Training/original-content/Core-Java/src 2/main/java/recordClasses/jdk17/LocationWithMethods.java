package recordClasses.jdk17;

record LocationWithMethods(double latitude, double longitude) {

    public double distanceTo(LocationWithMethods other) {
        return 0.0;
    }

    // Outlines for other methods
    public LocationWithMethods offsetBy(double deltaLatitude, double deltaLongitude) {
        return new LocationWithMethods(latitude + deltaLatitude, longitude + deltaLongitude);
    }
}
