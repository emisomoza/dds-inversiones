package ar.edu.utn.dds.filter;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.integration.file.filters.AbstractFileListFilter;
import org.springframework.integration.file.filters.ResettableFileListFilter;
import org.springframework.integration.file.filters.ReversibleFileListFilter;

import java.io.File;
import java.util.List;

public class RedisAcceptOnceFileListFilter<F> extends AbstractFileListFilter<F> implements ReversibleFileListFilter<F>, ResettableFileListFilter<F> {

    private RedisTemplate redisTemplate;

    public RedisAcceptOnceFileListFilter(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected boolean accept(F f) {
        String key = ((File) f).getName();
        if (!redisTemplate.hasKey(key)) {
            redisTemplate.opsForValue().set(key, "");
            return true;
        }

        return false;
    }

    @Override
    public boolean remove(F f) {
        redisTemplate.delete(((File) f).getName());
        return true;
    }

    @Override
    public void rollback(F f, List<F> list) {
        boolean rollingBack = false;
        for (F fileToRollback : list) {
            if (fileToRollback.equals(f)) {
                rollingBack = true;
            }
            if (rollingBack) {
                remove(fileToRollback);
            }
        }
    }
}
