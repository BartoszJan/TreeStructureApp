package com.bjanczak.service;

import com.bjanczak.model.dto.NodeDto;
import com.bjanczak.repository.NodeRepository;
import com.bjanczak.service.impl.NodeServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
public class NodeServiceImplTest {

    @TestConfiguration
    static class NodeServiceImplTestContextConfiguration {

        @Bean
        public NodeService employeeService() {
            return new NodeServiceImpl();
        }
    }

    @Autowired
    private NodeService nodeService;

    @MockBean
    private NodeRepository nodeRepository;

    @Test
    public void getRootWithChildrenTest() {
        List<NodeDto> nodes = nodeService.getRootWithChildren();

        assertThat(nodes)
                .isNotNull()
                .isNotEmpty();
    }
}
