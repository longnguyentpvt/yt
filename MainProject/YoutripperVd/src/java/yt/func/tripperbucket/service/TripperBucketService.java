/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.tripperbucket.service;

import java.io.IOException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hiep
 */
public interface TripperBucketService {

    public String addToBucketList(HttpSession session, String data) throws IOException;

    public String loadBuckets(HttpSession session) throws IOException;

    public String removeBuckets(HttpSession session, String data) throws IOException;

}
