package my.comp.config;

public class SysConfLoader {
	private String[] resources;

	public String[] getResources() {
		return resources;
	}

	public void setResources(String[] resources) {
		this.resources = resources;
	}

	public void init() {
		SysConf.load(resources);
	}

}
