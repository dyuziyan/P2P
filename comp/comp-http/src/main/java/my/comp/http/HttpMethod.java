package my.comp.http;


public interface HttpMethod<R, P> {
	R invoke(String url, P params) throws HttpException;
}
