/*
Licensed to the Apache Software Foundation (ASF) under one
or more contributor license agreements.  See the NOTICE file
distributed with this work for additional information
regarding copyright ownership.  The ASF licenses this file
to you under the Apache License, Version 2.0 (the
"License"); you may not use this file except in compliance
with the License.  You may obtain a copy of the License at

  http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing,
software distributed under the License is distributed on an
"AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
KIND, either express or implied.  See the License for the
specific language governing permissions and limitations
under the License.
*/
package org.apache.plc4x.java.bacnetip;

import org.apache.plc4x.java.bacnetip.configuration.PassiveBacNetIpConfiguration;
import org.apache.plc4x.java.bacnetip.protocol.PassiveBacNetIpProtocolLogic;
import org.apache.plc4x.java.bacnetip.readwrite.BVLC;
import org.apache.plc4x.java.bacnetip.readwrite.io.BVLCIO;
import org.apache.plc4x.java.spi.configuration.Configuration;
import org.apache.plc4x.java.spi.connection.ProtocolStackConfigurer;
import org.apache.plc4x.java.spi.connection.SingleProtocolStackConfigurer;

public class PassiveBacNetIpDriver extends AbstractBacNetIpDriver {

    @Override
    protected Class<? extends Configuration> getConfigurationType() {
        return PassiveBacNetIpConfiguration.class;
    }

    @Override
    protected String getDefaultTransport() {
        return "udp";
    }

    @Override
    protected boolean canRead() {
        return false;
    }

    @Override
    protected boolean canWrite() {
        return false;
    }

    @Override
    protected boolean canSubscribe() {
        return true;
    }

    @Override
    protected ProtocolStackConfigurer<BVLC> getStackConfigurer() {
        return SingleProtocolStackConfigurer.builder(BVLC.class, BVLCIO.class)
            .withProtocol(PassiveBacNetIpProtocolLogic.class)
            .withPacketSizeEstimator(ByteLengthEstimator.class)
            .withCorruptPacketRemover(CorruptPackageCleaner.class)
            .build();
    }


}
