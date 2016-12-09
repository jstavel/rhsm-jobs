job('RHSM-QE Seed Job') {
    scm {
        github 'RedHatQE/rhsm-jobs'
    }
    triggers {
        scm 'H/5 * * * *'
    }
    steps {
        gradle 'clean test'
        dsl {
            external 'jobs/**/*Job.groovy'
            additionalClasspath 'src/main/groovy'
        }
    }
    publishers {
        publishHtml {
            report('build/reports/tests/') {
                reportName('Grade Test Results')
            }
        }
    }
}
