package dev.vince.maven.manager.bean.cache;

import dev.vince.maven.manager.bean.search.MavenSearchBean;
import lombok.Data;

/**
 * The SearchCacheEntryBean class is used to represent a search cache entry.
 * This class is used internally by the central-maven library.
 * 
 * @author dev-vince
 * @version 1.0.0
 * @since 1.0.0
 */
@Data
public final class SearchCacheEntryBean {
    MavenSearchBean search;
    byte[] base64Data;
}
