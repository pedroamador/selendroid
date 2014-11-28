/*
 * Copyright 2012-2014 eBay Software Foundation and selendroid committers.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.selendroid.server.server.handler;

import org.json.JSONException;
import org.json.JSONObject;

import io.selendroid.server.server.SafeRequestHandler;
import io.selendroid.server.server.model.AndroidElement;
import io.selendroid.server.server.model.TouchScreen;
import io.selendroid.server.server.model.interactions.Coordinates;
import io.selendroid.server.util.SelendroidLogger;
import io.selendroid.server.common.Response;
import io.selendroid.server.common.SelendroidResponse;
import io.selendroid.server.common.http.HttpRequest;

public class SingleTapOnElement extends SafeRequestHandler {

  public SingleTapOnElement(String mappedUri) {
    super(mappedUri);
  }

  @Override
  public Response safeHandle(HttpRequest request) throws JSONException {
    SelendroidLogger.info("single tap on element gesture");
    JSONObject payload = getPayload(request);
    String elementId = payload.getString("element");

    AndroidElement element = getElementFromCache(request, elementId);
    TouchScreen touchScreen = getSelendroidDriver(request).getTouch();
    
    Coordinates elementLocation = element.getCoordinates();

    touchScreen.singleTap(elementLocation);
    return new SelendroidResponse(getSessionId(request), "");
  }

}