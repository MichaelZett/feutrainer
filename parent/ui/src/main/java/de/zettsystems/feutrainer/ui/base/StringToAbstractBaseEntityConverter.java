package de.zettsystems.feutrainer.ui.base;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.vaadin.data.util.converter.Converter;

import de.zettsystems.feutrainer.domain.base.AbstractBaseEntity;

public class StringToAbstractBaseEntityConverter implements Converter<String, AbstractBaseEntity> {
	private static final Logger LOGGER = LoggerFactory.getLogger(StringToAbstractBaseEntityConverter.class);

	@Override
	public AbstractBaseEntity convertToModel(String value, Class<? extends AbstractBaseEntity> targetType,
			Locale locale) throws com.vaadin.data.util.converter.Converter.ConversionException {
		// should not be used
		LOGGER.warn("convertToModel used, should not happen");
		return null;
	}

	@Override
	public String convertToPresentation(AbstractBaseEntity value, Class<? extends String> targetType, Locale locale)
			throws com.vaadin.data.util.converter.Converter.ConversionException {
		return value.toPresentation();
	}

	@Override
	public Class<AbstractBaseEntity> getModelType() {
		return AbstractBaseEntity.class;
	}

	@Override
	public Class<String> getPresentationType() {
		return String.class;
	}

}
