<%-- 
    Document   : PartnerAccountCommon
    Created on : Jun 19, 2018, 11:57:10 AM
    Author     : nickn
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="account-common">
    <div class="ava">
        <img src="https://cdn.youtripper.com/partner-vb-programmer/pa20102017-1508497211342.jpg"/>
        <div class="upload-btn">
            <div class="vertical-center-container">
                <div class="center-content">
                    <div class="btn">
                        <svg width="20px" height="13.3px" viewBox="0 0 20 13.3" >
                        <path style="fill: #fff" d="M10.1,3.3c-2.2,0-4,1.8-4,4s1.8,4,4,4s4-1.8,4-4S12.3,3.3,10.1,3.3z"></path>
                        <path style="fill: #fff" d="M18.1,1.3h-2.6c-0.2,0-0.4-0.1-0.6-0.3c-0.4-0.6-0.7-1-1.1-1h-7C6.4,0,6.1,0.5,5.7,1
                              C5.6,1.2,5.4,1.3,5.2,1.3H2.1C1,1.3,0,2.2,0,3.3v8c0,1.1,1,2,2.1,2h16c1.1,0,1.9-0.9,1.9-2v-8C20,2.2,19.2,1.3,18.1,1.3z M2.1,4.5
                              c-0.6,0-1-0.4-1-1s0.4-1,1-1s1,0.4,1,1S2.6,4.5,2.1,4.5z M10.1,12c-2.6,0-4.7-2.1-4.7-4.7s2.1-4.7,4.7-4.7c2.6,0,4.7,2.1,4.7,4.7
                              S12.6,12,10.1,12z"></path>
                        </svg><br/>
                        <spring:message code="yt.partner.accountsetting.leftcommon.ava.btn"/>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="secs">
        <ul>
            <li>
                <div class="aSec">
                    <div class="sec-tt">
                        Display Name
                    </div>
                    <div class="sec-bd">
                        <div class="info-ls">
                            <ul>
                                <li>
                                    <div class="al">
                                        <div class="clearfix">
                                            <div class="i">
                                                <spring:message code="yt.partner.accountsetting.leftcommon.business.pid.txt"/>
                                            </div>
                                            <div class="v">
                                                P000000000
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="al">
                                        <div class="clearfix">
                                            <div class="i">
                                                <spring:message code="yt.partner.accountsetting.leftcommon.business.regdate.txt"/>
                                            </div>
                                            <div class="v">
                                                00/00/0000
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="aSec">
                    <div class="sec-tt">
                        <spring:message code="yt.partner.accountsetting.leftcommon.detail.tt"/>
                    </div>
                    <div class="sec-bd">
                        <div class="info-ls">
                            <ul>
                                <li>
                                    <div class="al">
                                        <div class="clearfix">
                                            <div class="i">
                                                <spring:message code="yt.partner.accountsetting.leftcommon.detail.ttpkg.txt"/>
                                            </div>
                                            <div class="v">
                                                000000
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="al">
                                        <div class="clearfix">
                                            <div class="i">
                                                <spring:message code="yt.partner.accountsetting.leftcommon.detail.soldpkg.txt"/>
                                            </div>
                                            <div class="v">
                                                00000000
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="al">
                                        <div class="clearfix">
                                            <div class="i">
                                                <spring:message code="yt.partner.accountsetting.leftcommon.detail.cancelpkg.txt"/>
                                            </div>
                                            <div class="v">
                                                00000000
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="al">
                                        <div class="clearfix">
                                            <div class="i">
                                                <spring:message code="yt.partner.accountsetting.leftcommon.detail.refundpkg.txt"/>
                                            </div>
                                            <div class="v">
                                                00000000
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="al">
                                        <div class="clearfix">
                                            <div class="i">
                                                <spring:message code="yt.partner.accountsetting.leftcommon.detail.vstime.txt"/>
                                            </div>
                                            <div class="v">
                                                00/00/0000
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </li>
            <li>
                <div class="aSec">
                    <div class="sec-tt">
                        <spring:message code="yt.partner.accountsetting.leftcommon.verification.tt"/>
                    </div>
                    <div class="sec-bd">
                        <div class="info-ls">
                            <ul>
                                <li>
                                    <div class="al">
                                        <div class="clearfix">
                                            <div class="i">
                                                <spring:message code="yt.partner.accountsetting.leftcommon.verification.perid.txt"/>
                                            </div>
                                            <div class="v">
                                                <div class="cb">
                                                    <div class="checkbox-ctn">
                                                        <div class="yt-checkbox">
                                                            <input type="checkbox" name="" value="ON">
                                                            <span></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="al">
                                        <div class="clearfix">
                                            <div class="i">
                                                <spring:message code="yt.partner.accountsetting.leftcommon.verification.taxid.txt"/>
                                            </div>
                                            <div class="v">
                                                <div class="cb">
                                                    <div class="checkbox-ctn">
                                                        <div class="yt-checkbox">
                                                            <input type="checkbox" name="" value="ON">
                                                            <span></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="al">
                                        <div class="clearfix">
                                            <div class="i">
                                                <spring:message code="yt.partner.accountsetting.leftcommon.verification.comreg.txt"/>
                                            </div>
                                            <div class="v">
                                                <div class="cb">
                                                    <div class="checkbox-ctn">
                                                        <div class="yt-checkbox">
                                                            <input type="checkbox" name="" value="ON">
                                                            <span></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="al">
                                        <div class="clearfix">
                                            <div class="i">
                                                <spring:message code="yt.partner.accountsetting.leftcommon.verification.bankacc.txt"/>
                                            </div>
                                            <div class="v">
                                                <div class="cb">
                                                    <div class="checkbox-ctn">
                                                        <div class="yt-checkbox">
                                                            <input type="checkbox" name="" value="ON">
                                                            <span></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                                <li>
                                    <div class="al">
                                        <div class="clearfix">
                                            <div class="i">
                                                <spring:message code="yt.partner.accountsetting.leftcommon.verification.email.txt"/>
                                            </div>
                                            <div class="v">
                                                <div class="cb">
                                                    <div class="checkbox-ctn">
                                                        <div class="yt-checkbox">
                                                            <input type="checkbox" name="" value="ON">
                                                            <span></span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</div>
