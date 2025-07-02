package com.eon.springbatch.config;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.eon.springbatch.entity.Product;

@Configuration
public class BatchConfig {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Bean
	public Job jobBean(JobRepository jobRepository, JobCompletationNotificationImpl listener, Step step) {
		return new JobBuilder("job", jobRepository).listener(listener).start(step).build();

	}

	@Bean
	public Step steps(JobRepository jobRepository, DataSourceTransactionManager trnsManagere, FlatFileItemReader<Product> reader,
			ItemProcessor<Product, Product> processor, ItemWriter<Product> writer) {
		return new StepBuilder("jobStep", jobRepository).<Product, Product>chunk(5, trnsManagere).reader(reader)
				.processor(processor).writer(writer).build();

	}

// Reader
	@Bean
	public FlatFileItemReader<Product> reader() {
		logger.info("Reader Started");
		return new FlatFileItemReaderBuilder<Product>().name("itemReader").resource(new ClassPathResource("data.csv"))
				.linesToSkip(1)  // ✅ skip header
				.delimited().names("productId", "title", "description", "price", "discount").targetType(Product.class)
				.build();
	}

	@Bean
	public ItemProcessor<Product, Product> itemProcessor() {
		logger.info("Item Started");
		return new CustomItemProcessor();
	}

	// writer
	@Bean
	public ItemWriter<Product> itemWriter(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Product>().sql(
				"insert into Products(productId,title,description,price,discount,discounted_price)values(:productId, :title, :description, :price, :discount, :discountedPrice)")
				.dataSource(dataSource).beanMapped().build();
	}

}
