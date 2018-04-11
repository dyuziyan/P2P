package my.comp.springmvc.formatter;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import my.comp.lang.DateUtils;

import org.springframework.format.Formatter;

public class DateFormatter implements Formatter<Date>{

	@Override
	public String print(Date object, Locale locale) {
		return DateUtils.formatDate(object, "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss");
	}

	@Override
	public Date parse(String text, Locale locale) throws ParseException {
		return DateUtils.parseDate(text, new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"});
	}

}
