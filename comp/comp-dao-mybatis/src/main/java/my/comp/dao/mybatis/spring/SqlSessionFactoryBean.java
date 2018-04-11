package my.comp.dao.mybatis.spring;

import java.util.ArrayList;
import java.util.List;

import my.comp.lang.StringUtils;

import org.apache.commons.collections.CollectionUtils;

public class SqlSessionFactoryBean extends org.mybatis.spring.SqlSessionFactoryBean {

	private PackagePatternResolver packagePatternResolver;

	public void setPackagePatternResolver(PackagePatternResolver packagePatternResolver) {
		this.packagePatternResolver = packagePatternResolver;
	}

	public void setTypeAliasesPackage(String typeAliasesPackage) {

//		Assert.notNull(packagePatternResolver, "setTypeAliasesPackage must be before setPackagePatternResolver");
		if (packagePatternResolver == null) {
			packagePatternResolver = new SpringPackagePatternResolver();
		}

		List<String> packageList = new ArrayList<String>();

		String[] patterns = StringUtils.split(typeAliasesPackage, ",");

		for (String pattern : patterns) {
			String[] packages = packagePatternResolver.resolve(StringUtils.trim(pattern));
			CollectionUtils.addAll(packageList, packages);
		}
		super.setTypeAliasesPackage(StringUtils.join(packageList, ", "));
	}
}
