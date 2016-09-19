package com.yungdu.us.squash.converters;

/**
 * 
 */
public interface Item2DtoConverter<T extends Object, M extends Object> {
	/**
	 * Convert the given Object to corresponding DTO
	 * 
	 * @param item the item to convert
	 * @return the output dto
	 */
	M convert(T item);
}
