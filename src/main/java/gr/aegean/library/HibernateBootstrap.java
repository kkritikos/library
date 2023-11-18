package gr.aegean.library;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateBootstrap {
	
	private static SessionFactory factory = null;
	
	public static SessionFactory getSessionFactory() {
		if (factory == null) {
			final StandardServiceRegistry registry =
		            new StandardServiceRegistryBuilder()
		                    .build();     
		    try {
		        Metadata metadata = 
		                new MetadataSources(registry)             
		                        .addAnnotatedClass(gr.aegean.library.Library.class)
		                        .addAnnotatedClass(gr.aegean.library.LendingBook.class)
		                        .addAnnotatedClass(gr.aegean.library.SellingBook.class)
		                        .addAnnotatedClass(gr.aegean.library.LibraryMember.class)
		                        .addAnnotatedClass(gr.aegean.library.Library.class)
		                        .getMetadataBuilder()
		                        .applyImplicitNamingStrategy(ImplicitNamingStrategyJpaCompliantImpl.INSTANCE)
		                        .build(); 
		        factory = metadata.getSessionFactoryBuilder()
		        	    //.applyBeanManager(getBeanManager())
		        	    .build();         
		    }
		    catch (Exception e) {
		        // The registry would be destroyed by the SessionFactory, but we
		        // had trouble building the SessionFactory so destroy it manually.
		    	e.printStackTrace();
		        StandardServiceRegistryBuilder.destroy(registry);
		    }
		}
	    return factory;
	}
	
	public static void destroy() {
		if (factory != null) factory.close();
	}
	
}
