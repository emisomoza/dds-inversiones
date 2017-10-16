package ar.edu.utn.dds.auth

import grails.gorm.DetachedCriteria
import groovy.transform.ToString

import org.apache.commons.lang.builder.HashCodeBuilder

@ToString(cache=true, includeNames=true, includePackage=false)
class DDSUserDDSRole implements Serializable {

	private static final long serialVersionUID = 1

	DDSUser ddsUser
	DDSRole ddsRole

	DDSUserDDSRole(DDSUser u, DDSRole r) {
		this()
		ddsUser = u
		ddsRole = r
	}

	@Override
	boolean equals(other) {
		if (!(other instanceof DDSUserDDSRole)) {
			return false
		}

		other.ddsUser?.id == ddsUser?.id && other.ddsRole?.id == ddsRole?.id
	}

	@Override
	int hashCode() {
		def builder = new HashCodeBuilder()
		if (DDSUser) builder.append(ddsUser.id)
		if (DDSRole) builder.append(ddsRole.id)
		builder.toHashCode()
	}

	static DDSUserDDSRole get(long ddsUserId, long ddsRoleId) {
		criteriaFor(ddsUserId, ddsRoleId).get()
	}

	static boolean exists(long ddsUserId, long ddsRoleId) {
		criteriaFor(ddsUserId, ddsRoleId).count()
	}

	private static DetachedCriteria criteriaFor(long ddsUserId, long ddsRoleId) {
		DDSUserDDSRole.where {
			ddsUser == DDSUser.load(ddsUserId) &&
			ddsRole == DDSRole.load(ddsRoleId)
		}
	}

	static DDSUserDDSRole create(DDSUser ddsUser, DDSRole ddsRole, boolean flush = false) {
		def instance = new DDSUserDDSRole(ddsUser: ddsUser, ddsRole: ddsRole)
		instance.save(flush: flush, insert: true)
		instance
	}

	static boolean remove(DDSUser u, DDSRole r, boolean flush = false) {
		if (u == null || r == null) return false

		int rowCount = DDSUserDDSRole.where { ddsUser == u && ddsRole == r }.deleteAll()

		if (flush) { DDSUserDDSRole.withSession { it.flush() } }

		rowCount
	}

	static void removeAll(DDSUser u, boolean flush = false) {
		if (u == null) return

		DDSUserDDSRole.where { ddsUser == u }.deleteAll()

		if (flush) { DDSUserDDSRole.withSession { it.flush() } }
	}

	static void removeAll(DDSRole r, boolean flush = false) {
		if (r == null) return

		DDSUserDDSRole.where { ddsRole == r }.deleteAll()

		if (flush) { DDSUserDDSRole.withSession { it.flush() } }
	}

	static constraints = {
		ddsRole validator: { DDSRole r, DDSUserDDSRole ur ->
			if (ur.ddsUser == null || ur.ddsUser.id == null) return
			boolean existing = false
			DDSUserDDSRole.withNewSession {
				existing = DDSUserDDSRole.exists(ur.ddsUser.id, r.id)
			}
			if (existing) {
				return 'userRole.exists'
			}
		}
	}

	static mapping = {
		id composite: ['ddsUser', 'ddsRole']
		version false
	}
}
