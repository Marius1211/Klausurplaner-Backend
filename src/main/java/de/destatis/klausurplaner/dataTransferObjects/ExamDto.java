package de.destatis.klausurplaner.dataTransferObjects;

/**
 * @author Marius
 * 
 * Datentransferobjekt für Klausuren
 */
public record ExamDto(Long id, String classname, String stunde, String subject, String topic, String sonstiges) {
}
