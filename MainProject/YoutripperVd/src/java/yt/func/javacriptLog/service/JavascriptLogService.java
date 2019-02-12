/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package yt.func.javacriptLog.service;

import java.io.IOException;
import org.springframework.mobile.device.Device;

/**
 *
 * @author Hiep
 */
public interface JavascriptLogService {

    public String saveJavascriptLog(String data, Device device) throws IOException;

}
