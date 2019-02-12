/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.tripperbooking.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import javaclass.common.YTSession;
import javaclass.common.YTSessionAccount;
import javaclass.utility.YTFileUtility;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yt.func.tripperbooking.dao.YTBookingOrderDAO;
import yt.func.tripperbooking.javaclass.AdMyBooking;

/**
 *
 * @author Hiep
 */
@Service
public class TripperBookingServiceImpl implements TripperBookingService {

    @Autowired
    private YTBookingOrderDAO myBookingOrderDAO;

    @Override
    @Transactional
    public String loadTripperBooking(HttpSession session, String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode dataObject = mapper.readTree(data);
        //get page
        int page = dataObject.get("page").asInt();
        // get limit
        int limit = dataObject.get("limit").asInt();
        //booking code
        String bookingCode = null;
        JsonNode bookingCodeNode = dataObject.get("bookingCode");
        if (!bookingCodeNode.isNull()) {
            bookingCode = bookingCodeNode.asText();
            if (bookingCode.isEmpty()) {
                bookingCode = null;
            }
        }
        // get tripper ID
        YTSessionAccount account = YTSession.getAccountSession(session);
        String tripperID = account.getTripperID();
        int skipRows = (page * limit) - limit;
        //get list booking
        List<AdMyBooking> bList = myBookingOrderDAO.getMyBookingList(tripperID, skipRows, limit, bookingCode);
        for (AdMyBooking booking : bList) {
            String invoiceFile = booking.getInvoiceFile();
            if (invoiceFile != null) {
                invoiceFile = YTFileUtility.getInvoiceLink() + invoiceFile;
            }
            //
            booking.setInvoiceFile(invoiceFile);
        }
        String jsonString = mapper.writeValueAsString(bList);
        return jsonString;
    }

}
