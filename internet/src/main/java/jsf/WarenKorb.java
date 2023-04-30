package jsf;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.List;
import java.util.logging.Logger;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.annotation.ManagedProperty;
import javax.faces.event.PhaseId;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;


import internet.DbConnection;
import internet.Book;


@Named
@RequestScoped
public class WarenKorb {
    // @Inject
	// @ManagedProperty("#{kontoHandler.aktuellerKunde}")
	// private Kunde aktuellerKunde;

	private static DecimalFormat df = new DecimalFormat("0.00");
	private List<Book> bucher;
}
