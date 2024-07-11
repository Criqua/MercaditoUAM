package com.uam.mercaditouam.service;

import com.uam.mercaditouam.dto.PurchaseDTO;
import com.uam.mercaditouam.entities.Publication;
import com.uam.mercaditouam.entities.Purchase;
import com.uam.mercaditouam.entities.Student;
import com.uam.mercaditouam.repository.IRepoPublication;
import com.uam.mercaditouam.repository.IRepoPurchase;
import com.uam.mercaditouam.repository.IRepoStudent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicePurchase implements IServicePurchase{
    @Autowired
    private IRepoPurchase repoPurchase;
    @Autowired
    private IRepoPublication repoPublication;
    @Autowired
    private IRepoStudent repoStudent;

    @Override
    public List<Purchase> getALl() {
        return repoPurchase.findAll();
    }

    @Override
    public <T> T findById(Long id) {
        Purchase purchase = repoPurchase.findById(id).orElse(null);
        if(purchase == null) {
            return (T) ResponseEntity.badRequest().body("Purchase does not exist");
        }
        return (T) purchase;
    }

    @Override
    public ResponseEntity<String> createPurchase(PurchaseDTO purchaseDTO) {
        Purchase purchase = repoPurchase.findById(purchaseDTO.getId()).orElse(null);
        if(purchase == null) {
            purchase = new Purchase();
            purchase.setId(purchaseDTO.getId());
            purchase.setPurchaseDate(purchaseDTO.getPurchaseDate());
            Publication publication = repoPublication.findById(purchaseDTO.getPublicationId()).orElse(null);
            if(publication == null) {
                return ResponseEntity.badRequest().body("The publication does not exist");
            }
            purchase.setPublicationId(purchaseDTO.getPublicationId());
            Student student = repoStudent.findById(purchaseDTO.getStudentId()).orElse(null);
            if(student == null) {
                return ResponseEntity.badRequest().body("The sender student does not exist");
            }
            purchase.setStudentId(purchaseDTO.getStudentId());
            repoPurchase.save(purchase);
        } else if(repoPurchase.existsById(purchase.getId())) {
            return ResponseEntity.badRequest().body("Purchase already exists");
        }
        return ResponseEntity.ok("Purchase created");
    }

    @Override
    public ResponseEntity<String> updatePurchase(PurchaseDTO purchaseDTO) {
        Purchase purchase = repoPurchase.findById(purchaseDTO.getId()).orElse(null);
        if(purchase == null) {
            return ResponseEntity.badRequest().body("Purchase does not exist");
        }
        purchase.setPurchaseDate(purchaseDTO.getPurchaseDate());
        repoPurchase.save(purchase);
        return ResponseEntity.ok("Purchase updated");
    }

    @Override
    public ResponseEntity<String> deletePurchase(Long id) {
        Purchase purchase = repoPurchase.findById(id).orElse(null);
        if(purchase == null) {
            return ResponseEntity.badRequest().body("Purchase does not exist");
        }
        repoPurchase.deleteById(id);
        return ResponseEntity.ok("Purchase deleted");
    }
}
