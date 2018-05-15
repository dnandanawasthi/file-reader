package com.filereader

import com.filereader.FileDownloaderApp
import spock.lang.Ignore
import spock.lang.Specification

class FileDownloaderAppSpec extends Specification {

    FileDownloaderApp app = new FileDownloaderApp();

    def "successCase"() {
        when:
        def resultsCollector = app.testFunctionality(new URL("https://s3.amazonaws.com/swrve-public/full_stack_programming_test/test_data.csv.gz"));
        then:
        resultsCollector.getNumberOfDeviceResolution() == 28
        resultsCollector.getTotalSpend() == 51621
        resultsCollector.getFirstUserId() == "a888a1c57cf6af2ffee687bfdd7dc4c5"
        resultsCollector.getNumberOfUser() == 100
    }

}
