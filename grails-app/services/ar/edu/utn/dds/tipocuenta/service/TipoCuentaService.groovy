package ar.edu.utn.dds.tipocuenta.service

import ar.edu.utn.dds.cache.CacheData
import ar.edu.utn.dds.model.TipoCuenta
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable

class TipoCuentaService {

    def tipoCuentaRepository

    @Cacheable(cacheNames = CacheData.TIPO_CUENTA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    def listar() {
        return this.listar(new TipoCuenta())
    }

    def listar(TipoCuenta tipoCuenta) {
        return this.tipoCuentaRepository.listar(tipoCuenta)
    }

    @Cacheable(cacheNames = CacheData.TIPO_CUENTA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER)
    def obtener(Long id) {
        return this.tipoCuentaRepository.obtener(id)
    }

    @CacheEvict(cacheNames = CacheData.TIPO_CUENTA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    def guardar(TipoCuenta tipoCuenta) {
        return this.tipoCuentaRepository.guardar(tipoCuenta)
    }

    @CacheEvict(cacheNames = CacheData.TIPO_CUENTA_CACHE_NAME, cacheManager = CacheData.REDIS_CACHE_MANAGER, allEntries = true)
    def actualizar(TipoCuenta tipoCuenta) {
        return this.tipoCuentaRepository.actualizar(tipoCuenta)
    }

}
