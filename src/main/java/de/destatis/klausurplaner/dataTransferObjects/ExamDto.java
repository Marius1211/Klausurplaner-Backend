package de.destatis.klausurplaner.dataTransferObjects;

public record ExamDto(Long id, String classname, String stunde, String subject, String topic, String sonstiges) {
}
