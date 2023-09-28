package de.destatis.klausurplaner.dataTransferObjects;

/**
 * @author Patrick
 * 
 * Datentransferobjekt für Kalendereinträge
 */
public record CalendarDto(String schulstunde, String tag, String klausurArt) {
}
