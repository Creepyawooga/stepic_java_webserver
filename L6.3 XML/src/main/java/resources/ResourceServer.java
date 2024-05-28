package resources;

public class ResourceServer implements ResourceServerMBean {
    private TestResource resource;

    public void setResource(TestResource resource) {
        this.resource = resource;
    }

    @Override
    public String getName() {
        return resource != null ? resource.getName() : "";
    }

    @Override
    public int getAge() {
        return resource != null ? resource.getAge() : 0;
    }
}
