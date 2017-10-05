package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("cmd.converter.stringToDate")
public class StringToDateConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        if (string == null || string.isEmpty()) {
            return null;
        }
        
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy", Locale.getDefault());
            LocalDate date = LocalDate.parse(string, formatter);
            return date;
        } catch (IllegalArgumentException e) {
            throw new ConverterException(new FacesMessage(
                "Erro no formato sugerido de data."
            ));        
        } catch (DateTimeParseException e) {
            throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR,
                "Erro ao converter data.",
                "Por favor, insira uma data válida no formato dd/MM/yyyy."
            ));        
        }
                
    }
    

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return ((LocalDate)o).toString();
    }
    
}
