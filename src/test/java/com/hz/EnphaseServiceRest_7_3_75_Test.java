package com.hz;

import com.hz.configuration.TestEnphaseSystemInfoConfig;
import com.hz.models.envoy.xml.EnvoyInfo;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("testing")
@Import(TestEnphaseSystemInfoConfig.class)
@AutoConfigureWireMock(port = 0,stubs="classpath:/stubs/D7.3.75")
@Log4j2
class EnphaseServiceRest_7_3_75_Test {

	@TestConfiguration
	static class EnphaseServiceTestContextConfiguration {
		@Bean
		@Primary
		String mockEnvoyInfo() {
			return "<envoy_info>\n<time>1656815735</time>\n<device>\n<sn>############</sn>\n<pn>800-00555-r03</pn>\n<software>D7.3.75</software>\n<euaid>4c8675</euaid>\n<seqnum>0</seqnum>\n<apiver>1</apiver>\n<imeter>true</imeter>\n</device>\n<web-tokens>true</web-tokens>\n<package name=\"rootfs\">\n<pn>500-00001-r01</pn>\n<version>02.00.00</version>\n<build>1210</build>\n</package>\n<package name=\"kernel\">\n<pn>500-00011-r02</pn>\n<version>04.04.225</version>\n<build>3eb4d3</build>\n</package>\n<package name=\"boot\">\n<pn>590-00018-r01</pn>\n<version>02.00.01</version>\n<build>426697</build>\n</package>\n<package name=\"app\">\n<pn>500-00002-r01</pn>\n<version>07.03.75</version>\n<build>47f7da</build>\n</package>\n<package name=\"devimg\">\n<pn>500-00004-r01</pn>\n<version>01.02.376</version>\n<build>ee52f7</build>\n</package>\n<package name=\"geo\">\n<pn>500-00008-r01</pn>\n<version>02.01.24</version>\n<build>a74d96</build>\n</package>\n<package name=\"backbone\">\n<pn>500-00010-r01</pn>\n<version>07.00.20</version>\n<build>176d57</build>\n</package>\n<package name=\"meter\">\n<pn>500-00013-r01</pn>\n<version>03.02.08</version>\n<build>1dd67a</build>\n</package>\n<package name=\"agf\">\n<pn>500-00012-r01</pn>\n<version>02.02.00</version>\n<build>fb593e</build>\n</package>\n<package name=\"security\">\n<pn>500-00016-r01</pn>\n<version>02.00.00</version>\n<build>54a6dc</build>\n</package>\n<package name=\"essimg\">\n<pn>500-00020-r01</pn>\n<version>21.23.12</version>\n<build>58708c</build>\n</package>\n<package name=\"pkgsec\">\n<pn>500-00021-r01</pn>\n<version>01.00.00</version>\n<build>19ae14</build>\n</package>\n<build_info>\n<build_id>\nec2-user-envoy_uber-pkg_master:pkg-Mar-31-22-23:29:03\n</build_id>\n<build_time_gmt>1648769474</build_time_gmt>\n<release_ver>02.00.2449</release_ver>\n<release_stage>700-GA</release_stage>\n</build_info>\n</envoy_info>";
		}
	}

	@Autowired
	private EnvoyInfo envoyInfo;

	@Test
	void IdentifyVersion() {
		Assertions.assertEquals("D7.3.75", this.envoyInfo.getSoftwareVersion());
		Assertions.assertEquals("############", this.envoyInfo.getSerialNumber());
		Assertions.assertTrue(this.envoyInfo.webTokens);
		Assertions.assertTrue(this.envoyInfo.isV7orAbove());
	}
}
