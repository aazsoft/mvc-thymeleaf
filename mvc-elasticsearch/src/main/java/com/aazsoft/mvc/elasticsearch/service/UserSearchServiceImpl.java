package com.aazsoft.mvc.elasticsearch.service;

import static org.elasticsearch.index.query.FilterBuilders.*;

import java.util.List;
import java.util.Objects;

import org.apache.commons.lang.StringUtils;
import org.elasticsearch.index.query.AndFilterBuilder;
import org.elasticsearch.index.query.FilterBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aazsoft.mvc.domain.entity.User;
import com.aazsoft.mvc.domain.forms.UserSearchForm;
import com.aazsoft.mvc.elasticsearch.repos.UserESRepository;

@Service
@Transactional
public class UserSearchServiceImpl implements UserSearchService {

	@Autowired
	private UserESRepository userESRepo;

	@Override
	public List<User> searchAllUsersByEmail(String email) {
		return userESRepo.findByEmail(email);
	}

	@Override
	public Page<User> searchUsers(final UserSearchForm searchForm, final Pageable pageable) {
		final NativeSearchQueryBuilder queryBuilder = new NativeSearchQueryBuilder().withFilter(matchAllFilter());
		final AndFilterBuilder andFilterBuilder = FilterBuilders.andFilter();
		if (!StringUtils.isBlank(searchForm.getUsername())) {
			andFilterBuilder.add(regexpFilter("username", ".*" + searchForm.getUsername() + ".*"));
		}
		if (!StringUtils.isBlank(searchForm.getEmail())) {
			andFilterBuilder.add(termFilter("email", searchForm.getEmail()));
		}

		if (!StringUtils.isBlank(searchForm.getRole())) {
			andFilterBuilder.add(termFilter("role.name", searchForm.getRole()));
		}

		if (!Objects.isNull(searchForm.getAge())) {
			andFilterBuilder.add(rangeFilter("age").gte(searchForm.getAge()));
		}
		final SearchQuery searchQuery = queryBuilder.withFilter(andFilterBuilder).withPageable(pageable).build();

		return userESRepo.search(searchQuery);
	}
	
}
