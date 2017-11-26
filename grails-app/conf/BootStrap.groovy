import ar.edu.utn.dds.auth.DDSRole
import ar.edu.utn.dds.auth.DDSUser
import ar.edu.utn.dds.auth.DDSUserDDSRole

class BootStrap {

    def init = { servletContext ->
        // test users initialization - esto esta solo porque es un tp,
        // si fuera productivo claramente autenticariamos contra un oauth, eldap o blah
        def adminRole = new DDSRole('ROLE_ADMIN').save()
        def userRole = new DDSRole('ROLE_USER').save()

        def testAdminUser = new DDSUser('admin', 'password').save()
        def testUser = new DDSUser('user', 'password').save()

        DDSUserDDSRole.create(testAdminUser, adminRole, true)
        DDSUserDDSRole.create(testAdminUser, userRole, true)
        DDSUserDDSRole.create(testUser, userRole, true)

        assert DDSUser.count() == 2
        assert DDSRole.count() == 2
        assert DDSUserDDSRole.count() == 3
    }

    def destroy = {
    }
}
