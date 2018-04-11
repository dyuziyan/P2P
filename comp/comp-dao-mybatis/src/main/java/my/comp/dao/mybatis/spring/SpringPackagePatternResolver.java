package my.comp.dao.mybatis.spring;

import java.util.HashSet;
import java.util.Set;

import my.comp.dao.mybatis.spring.PackagePatternResolver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ClassUtils;

public class SpringPackagePatternResolver extends PathMatchingResourcePatternResolver implements PackagePatternResolver {

	private static final Logger logger = LoggerFactory.getLogger(SpringPackagePatternResolver.class);

	@Override
	public String[] resolve(String packagePattern) {

		Set<String> results = new HashSet<String>();
		try {
			String pathPattern = ClassUtils.convertClassNameToResourcePath(packagePattern);

			String pattern = ConfigurableApplicationContext.CLASSPATH_ALL_URL_PREFIX + pathPattern;

			if (logger.isDebugEnabled()) {
				logger.debug("解析包名路径：{}", pattern);
			}
			String pathRoot = determineRootDir(pathPattern);

			if (logger.isDebugEnabled()) {
				logger.debug("解析包名根路径：{} -> {}", packagePattern, pathRoot);
			}

			Resource[] resources = getResources(pattern);

			for (int i = 0; i < resources.length; i++) {

				String path = resources[i].getURI().toString();

				if (logger.isDebugEnabled()) {
					logger.debug("解析包名资源路径：{}", path);
				}

				String packagePath = path.substring(path.lastIndexOf(pathRoot));

				String packageName = ClassUtils.convertResourcePathToClassName(packagePath);

				if (packageName.endsWith(".")) packageName = packageName.substring(0, packageName.length() - 1);
				
				if (logger.isDebugEnabled()) {
					logger.debug("解析包名资源路径结果：{}", packageName);
				}
				
				results.add(packageName);

			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return results.toArray(new String[0]);
	}

}
