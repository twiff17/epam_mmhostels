package by.epam.hostelbeta.util;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.TimerTask;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

import by.epam.hostelbeta.dao.DAOException;
import by.epam.hostelbeta.dao.impl.CurrencyDAO;
import by.epam.hostelbeta.domain.entity.Currency;
import by.epam.hostelbeta.domain.entity.CurrencyRate;

/**
 * The Class CurrencyRateUpdater. Updates currencies every 24 hours.
 */
public class CurrencyRateUpdater extends TimerTask {

	/** The Constant LOGGER. */
	static final Logger LOGGER = LogManager.getLogger(CurrencyRateUpdater.class);

	/** The currency DAO. */
	private CurrencyDAO currencyDAO = new CurrencyDAO();

	/** The Constant CURRENCY_OFFICIAL_RATE. */
	private static final String CURRENCY_OFFICIAL_RATE = "Cur_OfficialRate";

	/** The Constant CURRENCY_SCALE. */
	private static final String CURRENCY_SCALE = "Cur_Scale";

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.util.TimerTask#run()
	 */
	@Override
	public void run() {
		JSONObject jsonCurrency = null;
		try {
			List<Currency> currencyList = currencyDAO.findAll();

			for (Currency currency : currencyList) {
				if (currency.getCurrencyId() != 0) {
					jsonCurrency = JsonReader.readJsonFromUrl("http://www.nbrb.by/API/ExRates/Rates/"
							+ currency.getCurrencyId() + "?onDate=" + LocalDate.now());

					if (jsonCurrency != null) {
						CurrencyRate rate = new CurrencyRate();
						rate.setCode(currency.getCode());
						rate.setDate(LocalDate.now());
						rate.setRate(
								jsonCurrency.getDouble(CURRENCY_OFFICIAL_RATE) / jsonCurrency.getInt(CURRENCY_SCALE));

						currencyDAO.insertCurrencyRate(rate);
					}
				}
			}
			LOGGER.info("Currency rates are updated successfully");
		} catch (JSONException | IOException | DAOException e) {
			LOGGER.error("Error updating currency rate!", e);
		}
	}
}
