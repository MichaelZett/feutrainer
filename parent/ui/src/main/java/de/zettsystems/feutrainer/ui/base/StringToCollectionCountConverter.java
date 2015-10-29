package de.zettsystems.feutrainer.ui.base;

import java.util.Collection;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.util.converter.Converter;

@SuppressWarnings("rawtypes")
public class StringToCollectionCountConverter implements Converter<String, Collection> {
	private static final Logger LOGGER = LoggerFactory.getLogger(StringToCollectionCountConverter.class);

	@Override
	public Class<String> getPresentationType() {
		return String.class;
	}

	@Override
	public Class<Collection> getModelType() {
		return Collection.class;
	}

	@Override
	public Collection convertToModel(String value, Class<? extends Collection> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		LOGGER.warn("convertToModel used, should not happen");
		return null;
	}

	@Override
	public String convertToPresentation(Collection value, Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		return String.valueOf(value.size());
	}

}
